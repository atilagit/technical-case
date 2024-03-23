package com.example.technicalcase.services.validation;

import com.example.technicalcase.controller.data.requests.InsertCourseRequest;
import com.example.technicalcase.controller.exceptions.FieldMessage;
import com.example.technicalcase.entities.User;
import com.example.technicalcase.repositories.CourseRepository;
import com.example.technicalcase.repositories.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

import static com.example.technicalcase.enumerators.Role.INSTRUCTOR;
import static java.lang.Boolean.FALSE;
import static java.util.Objects.isNull;

public record InsertCourseValidator(CourseRepository courseRepository, UserRepository userRepository)
        implements ConstraintValidator<InsertCourseValidation, InsertCourseRequest> {

    @Override
    public void initialize(InsertCourseValidation constraintAnnotation) {
    }

    @Override
    public boolean isValid(InsertCourseRequest request, ConstraintValidatorContext constraintValidatorContext) {
        List<FieldMessage> fieldMessages = new ArrayList<>();

        var course = courseRepository.findByCode(request.code());
        if(course != null) {
            fieldMessages.add(new FieldMessage("code", request.code() + " already exists."));
        }

        var user = userRepository.findByUsername(request.instructorUsername());
        if(isNull(user)) {
            fieldMessages.add(new FieldMessage("instructorUsername", request.instructorUsername() + " does not exist in the database"));
        } else if(FALSE.equals(isAnInstructor(user))) {
            fieldMessages.add(new FieldMessage("instructorUsername", request.instructorUsername() + " is not an instructor"));
        }

        for (FieldMessage e : fieldMessages) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate(e.message()).addPropertyNode(e.fieldName())
                    .addConstraintViolation();
        }
        return fieldMessages.isEmpty();
    }

    private boolean isAnInstructor(User user) {
        return user.getRole().equals(INSTRUCTOR);
    }

}