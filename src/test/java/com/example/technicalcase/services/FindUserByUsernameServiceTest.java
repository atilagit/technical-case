package com.example.technicalcase.services;

import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import com.example.technicalcase.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindUserByUsernameServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    FindUserByUsernameService service;

    @Test
    public void shouldThrowResourceNotFoundException_WhenUserIsNull(){
        // Arrange
        String username = "nonExistingUser";
        when(repository.findByUsername(username)).thenReturn(null);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> service.execute(username));
    }

    @Test
    public void shouldReturnUser_WhenUserExists(){
        // Arrange
        String username = "existingUser";
        User user = new User();
        when(repository.findByUsername(username)).thenReturn(user);

        // Act
        User result = service.execute(username);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
    }
}