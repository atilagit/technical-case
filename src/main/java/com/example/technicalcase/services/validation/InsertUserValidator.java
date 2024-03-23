package com.example.technicalcase.services.validation;

import com.example.technicalcase.controller.data.requests.UserRequest;
import com.example.technicalcase.controller.exceptions.FieldMessage;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public record InsertUserValidator(UserRepository repository) implements ConstraintValidator<InsertUserValidation, UserRequest> {

    @Override
    public void initialize(InsertUserValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(UserRequest request, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        var email = request.email();
        User user = repository.findByEmail(email);
        if(user != null) {
            fieldMessages.add(new FieldMessage("email", "Email " + email + " already exists"));
        }

        var username = request.username();
        user = repository.findByUsername(username);
        if(user != null) {
            fieldMessages.add(new FieldMessage("username", "Username " + username +" already exists"));
        }

        for (FieldMessage e : fieldMessages) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
                    .addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }
}