package com.example.technicalcase.services;

import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class SaveUserService {

    @Autowired
    UserRepository repository;

    @Transactional
    public User execute(User user) {
        user.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return repository.save(user);
    }
}