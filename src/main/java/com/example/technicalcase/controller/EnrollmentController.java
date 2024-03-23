package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.InsertEnrollmentRequest;
import com.example.technicalcase.controller.data.responses.InsertEnrollmentResponse;
import com.example.technicalcase.services.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.technicalcase.controller.mappers.EnrollmentMapper.mapToEntity;
import static com.example.technicalcase.controller.mappers.EnrollmentMapper.mapToInsertEnrollmentResponse;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    EnrollmentService service;

    @PostMapping
    ResponseEntity<InsertEnrollmentResponse> saveCourse(@RequestBody @Valid InsertEnrollmentRequest requestDTO) {
        var enrollment = mapToEntity(requestDTO);
        enrollment = service.save(enrollment);
        var responseDTO = mapToInsertEnrollmentResponse(enrollment);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }
}