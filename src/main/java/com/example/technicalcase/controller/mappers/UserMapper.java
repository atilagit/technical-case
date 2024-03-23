package com.example.technicalcase.controller.mappers;

import com.example.technicalcase.controller.data.requests.UserRequest;
import com.example.technicalcase.controller.data.responses.UserResponse;
import com.example.technicalcase.entities.User;

import static org.springframework.beans.BeanUtils.copyProperties;

public class UserMapper {
    public static UserResponse mapToUserResponse(User user) {
        return new UserResponse(user.getId(), user.getName(), user.getUsername(), user.getEmail(), user.getRole(), user.getCreationDate());
    }

    public static User mapToUser(UserRequest request) {
        var user = new User();
        copyProperties(request, user);
        return user;
    }
}
