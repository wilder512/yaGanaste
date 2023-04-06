package org.example.BackEnd.RestController.Interface;

import org.example.BackEnd.DTO.UserDtod;
import org.example.BackEnd.DTO.UserLogin;
import org.example.BackEnd.Entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface IUserDynamoDBController {
    @GetMapping("/UserDynamoDb")
    ResponseEntity<Iterable<User>> listUser();

    @GetMapping("/UserDynamoDb/{userid}")
    ResponseEntity<User> listUserbyId(@PathVariable("userid") String userid);


    @GetMapping("/UserDynamoDb/delete/{userid}")
    ResponseEntity<String> delete(@PathVariable("userid") String userid);


    @PostMapping("/UserDynamoDb/save")
    ResponseEntity<User> save(@RequestBody UserDtod userDtod);

    @PutMapping("/UserDynamoDb/update")
    ResponseEntity<User> update(@RequestBody UserDtod userDtod);

    @PostMapping("/User/login")
    public ResponseEntity<String> login(@RequestBody UserLogin userLogin);

}
