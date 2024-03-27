package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.UserRequest;
import com.example.technicalcase.controller.data.responses.FindUserResponse;
import com.example.technicalcase.controller.data.responses.UserResponse;
import com.example.technicalcase.services.FindUserByUsernameService;
import com.example.technicalcase.services.SaveUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.technicalcase.controller.mappers.UserMapper.mapToUser;
import static com.example.technicalcase.controller.mappers.UserMapper.mapToUserResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    FindUserByUsernameService findUserByUsernameService;

    @Autowired
    SaveUserService saveUserService;

    @PostMapping
    ResponseEntity<UserResponse> saveUser(@RequestBody @Valid UserRequest userRequest) {
        var user = mapToUser(userRequest);
        user = saveUserService.execute(user);
        var userResponse = mapToUserResponse(user);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}").buildAndExpand(userResponse.username()).toUri();
        return ResponseEntity.created(uri).body(userResponse);
    }

    @GetMapping("/{username}")
    ResponseEntity<FindUserResponse> findUser(@PathVariable String username) {
        var user = findUserByUsernameService.execute(username);
        return ResponseEntity.ok(new FindUserResponse(user.getName(), user.getEmail(), user.getRole()));
    }
}
