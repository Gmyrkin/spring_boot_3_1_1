package com.example.education_3_1_1.service;

import com.example.education_3_1_1.model.User;
import com.example.education_3_1_1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
public class UserService implements UserServiceImpl{

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    @Transactional
    public User findById (Long id){
        return userRepository.getOne(id);
    }
    @Override
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }
    @Override
    @Transactional
    public void saveUser (User user){
        userRepository.save(user);
    }
    @Override
    public void deleteById (Long id){
        userRepository.deleteById(id);

    }
}
