package com.example.technicalcase.services;

import com.example.technicalcase.entities.User;
import org.springframework.stereotype.Service;

@Service
public class SaveUserUseCase {
    public User execute(User user) {
        user.setId(1L);
        return user;
    }
}