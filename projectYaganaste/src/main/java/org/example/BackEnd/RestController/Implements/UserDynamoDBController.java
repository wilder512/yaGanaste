package org.example.BackEnd.RestController.Implements;

import org.example.BackEnd.DTO.UserLogin;
import org.example.BackEnd.Entity.User;
import org.example.BackEnd.RestController.Interface.IUserDynamoDBController;
import org.example.BackEnd.Service.Implements.UserServiceImplement;
import org.example.BackEnd.DTO.UserDtod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
public class UserDynamoDBController implements IUserDynamoDBController {

    @Autowired
     private UserServiceImplement userService;

    public ResponseEntity<Iterable<User>> listUser()
    {
        return  ResponseEntity.ok(userService.listUser());
    }


    public ResponseEntity<User> listUserbyId(@PathVariable("userid") String userid)
    {
        if (!userService.existsId(userid))
        {
            return new ResponseEntity("Usuario No Existe", HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(userService.getUser(userid));
    }


    public ResponseEntity<String> delete(@PathVariable("userid") String userid)
    {
        if (!userService.existsId(userid))
        {
            return new ResponseEntity("Usuario No Existe", HttpStatus.NOT_FOUND);
        }
        userService.delete(userid);
        return  ResponseEntity.ok("Usuario eliminado!");
    }



    public ResponseEntity<User> save(@RequestBody UserDtod userDtod)
    {
        if (userService.existsId(userDtod.getUserid()))
        {
            return new ResponseEntity("Usuario Existe", HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserName(userDtod.getUsername());
        if(user.getUsername().equals(userDtod.getUsername()))
        {
            return new ResponseEntity("este username ya existe ", HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(userService.saveUser(userDtod));
    }


    //@PutMapping("/UserDynamoDb/update")
    public ResponseEntity<User> update(@RequestBody UserDtod userDtod)
    {
        if (!userService.existsId(userDtod.getUserid()))
        {
            return new ResponseEntity("Usuario No Existe", HttpStatus.NOT_FOUND);
        }
        User user = userService.getUserName(userDtod.getUsername());
        if(user.getUsername().equals(userDtod.getUsername()))
        {
            return new ResponseEntity("este username ya existe ", HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok(userService.updateUser(userDtod));
    }

    //@PostMapping("/User/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin)
    {
        Optional<User> user = Optional.ofNullable(userService.userLogin(userLogin.getUsername(), userLogin.getPassword()));

        if (!user.isPresent())
        {
            return new ResponseEntity("Usuario  NO Existe", HttpStatus.NOT_FOUND);
        }
        return  ResponseEntity.ok("usuario logeado");
    }
}
