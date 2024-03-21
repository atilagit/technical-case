package com.example.technicalcase.controller.data.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class UserResponseDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String role;
    private LocalDate creationDate;
}
