package com.example.technicalcase.services.validation;

import com.example.technicalcase.controller.data.requests.InsertCourseRequest;
import com.example.technicalcase.controller.exceptions.FieldMessage;
import com.example.technicalcase.entities.Course;
import com.example.technicalcase.repositories.CourseRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public record InsertCourseValidator(CourseRepository repository)
        implements ConstraintValidator<InsertCourseValidation, InsertCourseRequest> {

    @Override
    public void initialize(InsertCourseValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(InsertCourseRequest request, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        Course course = repository.findByCode(request.code());
        if(course != null) {
            fieldMessages.add(new FieldMessage("code", request.code() + " already exists."));
        }

        for (FieldMessage e : fieldMessages) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
                    .addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }
}