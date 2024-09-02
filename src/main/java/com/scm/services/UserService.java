package com.scm.services;

import java.util.List;
import java.util.Optional;

import com.scm.entities.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(String id);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String id); 
    List<User> getAllUser();
    Optional<User> findByEmail(String email);
    boolean isUserExistByEmail(String email); 
    User getUserByEmail(String email);

    //add more method here related user service logic

}
