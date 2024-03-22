package com.example.technicalcase.services;

import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveUserUseCase {

    @Autowired
    UserRepository repository;

    @Transactional
    public User execute(User user) {
        return repository.save(user);
    }
}