package com.example.import_export_excel.domain;

import com.example.import_export_excel.database.entity.User;
import com.example.import_export_excel.database.repository.UserRepository;
import com.example.import_export_excel.model.MainResponse;
import com.example.import_export_excel.model.request.UserRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDomain {
    @Autowired
    private UserRepository userRepository;

    public User createdUser(User user){
        return userRepository.save(user);
    }

    public Iterable<User> getAllUser(){
        return userRepository.findAll();
    }

    public Iterable<User> createdAllUser(Iterable<User> users){
        return userRepository.saveAll(users);
    }

}
