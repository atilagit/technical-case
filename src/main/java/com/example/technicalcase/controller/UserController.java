package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.UserRequest;
import com.example.technicalcase.controller.data.responses.UserResponse;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.services.SaveUserUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.time.LocalDate;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    SaveUserUseCase saveUserUseCase;

    @PostMapping
    ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {
        var user = mapToUser(userRequest);
        user = saveUserUseCase.execute(user);
        UserResponse userResponse = mapToUserResponse(user);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(userResponse.id()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreationDate());
    }

    private User mapToUser(UserRequest request) {
        var user = new User(LocalDate.now());
        BeanUtils.copyProperties(request, user);
        return user;
    }
}
