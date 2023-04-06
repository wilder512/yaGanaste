package org.example.BackEnd.Service.Interfaces;

import org.example.BackEnd.DTO.UserDtod;
import org.example.BackEnd.Entity.User;


public interface UserService {
    User saveUser(UserDtod userdto);
    User getUser(String userId);
    Iterable<User> listUser();
    User updateUser(UserDtod userdto);
    void delete(String userId);
    boolean existsId(String userId);
    User userLogin(String userName, String password);
}
