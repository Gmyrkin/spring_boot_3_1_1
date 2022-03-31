package com.example.education_3_1_1.service;

import com.example.education_3_1_1.model.User;

import java.util.List;

public interface UserServiceImpl {
    User findById (Long id);
    List<User> findAll ();
    void saveUser (User user);
    void deleteById (Long id);

}
