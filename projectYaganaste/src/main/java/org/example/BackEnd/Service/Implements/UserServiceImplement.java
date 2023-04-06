package org.example.BackEnd.Service.Implements;

import org.example.BackEnd.DTO.UserDtod;
import org.example.BackEnd.Entity.User;
import org.example.BackEnd.Repository.Interfaces.UserRepository;
import org.example.BackEnd.Service.Interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    private UserRepository userRepository;


    public Iterable<User> listUser() {
        return userRepository.findAll();
    }


    public User getUser(String userId) {
        return userRepository.findById(userId).get();
    }

    public User getUserName(String username) {
        return userRepository.findByUsername(username);
    }


    public User saveUser(UserDtod userdto) {
        User user = new User();
        user.setNombre(userdto.getNombre());
        user.setUserid(userdto.getUserid());
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword());
        return  userRepository.save(user);
    }


    public User updateUser(UserDtod userdto) {
        User user = userRepository.findById(userdto.getUserid()).get();
        user.setNombre(userdto.getNombre());
        user.setUserid(userdto.getUserid());
        user.setUsername(userdto.getUsername());
        user.setPassword(userdto.getPassword());
        return  userRepository.save(user);
    }

    public void delete(String userId) {
        userRepository.deleteById(userId);
    }


    public boolean existsId(String userId) {
        return userRepository.existsById(userId);
    }

    public User userLogin(String userName, String password) {
        return userRepository.findByUsernameAndPassword(userName,password);
    }
}
