package com.example.technicalcase.services;

import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SaveUserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    SaveUserService service;

    @Test
    public void shouldSaveUser() {
        // Arrange
        User user = new User();
        when(repository.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        User result = service.execute(user);

        // Assert
        assertNotNull(result);
        assertNotNull(result.getCreationDate());
    }
}
