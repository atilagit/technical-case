package com.example.technicalcase.services.validation;

import com.example.technicalcase.controller.data.requests.UserRequest;
import com.example.technicalcase.controller.exceptions.FieldMessage;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserRequest> {

    @Autowired
    private UserRepository repository;

    @Override
    public void initialize(UserInsertValid constraintAnnotation) {

    }

    @Override
    public boolean isValid(UserRequest request, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        User user = repository.findByEmail(request.email());
        if(user != null) {
            fieldMessages.add(new FieldMessage("email", "Email " + request.email() + " já existe"));
        }

        user = repository.findByUsername(request.username());
        if(user != null) {
            fieldMessages.add(new FieldMessage("username", "Username " + request.username() +" já existe"));
        }

        for (FieldMessage e : fieldMessages) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
                    .addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }
}