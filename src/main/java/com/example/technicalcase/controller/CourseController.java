package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.InsertCourseRequest;
import com.example.technicalcase.controller.data.responses.InsertCourseResponse;
import com.example.technicalcase.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.technicalcase.controller.mappers.CourseMapper.mapToCourse;
import static com.example.technicalcase.controller.mappers.CourseMapper.mapToInsertCourseResponse;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping
    ResponseEntity<InsertCourseResponse> saveCourse(@RequestBody @Valid InsertCourseRequest requestDTO) {
        var course = mapToCourse(requestDTO);
        course = service.save(course);
        var responseDTO = mapToInsertCourseResponse(course);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(responseDTO.code()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/inactivation/{code}")
    ResponseEntity<InsertCourseResponse> inactive(@PathVariable String code) {
        var course = service.inactive(code);
        var responseDTO = mapToInsertCourseResponse(course);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(responseDTO.code()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }
}
