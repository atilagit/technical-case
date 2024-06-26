package com.example.technicalcase.services;

import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class FindUserByUsernameService {

    @Autowired
    UserRepository repository;

    @Transactional(readOnly = true)
    public User execute(String username) {
        var user = repository.findByUsername(username);
        if (Objects.nonNull(user)){
            return user;
        }
        throw new ResourceNotFoundException();
    }
}