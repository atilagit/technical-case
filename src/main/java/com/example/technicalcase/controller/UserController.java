package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.UserRequesDTO;
import com.example.technicalcase.controller.data.responses.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserController {

    @PostMapping("/users")
    ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequesDTO userRequesDTO) {
        var responseDTO = UserResponseDTO.builder().id(1L).email(userRequesDTO.getEmail()).build();

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
