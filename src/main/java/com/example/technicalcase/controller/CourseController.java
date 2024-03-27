package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.InsertCourseRequest;
import com.example.technicalcase.controller.data.responses.FindAllCourseResponse;
import com.example.technicalcase.controller.data.responses.InsertCourseResponse;
import com.example.technicalcase.controller.mappers.CourseMapper;
import com.example.technicalcase.entities.Course;
import com.example.technicalcase.enumerators.Status;
import com.example.technicalcase.services.FindAllCourseService;
import com.example.technicalcase.services.InactiveCourseService;
import com.example.technicalcase.services.SaveCourseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.technicalcase.controller.mappers.CourseMapper.mapToCourse;
import static com.example.technicalcase.controller.mappers.CourseMapper.mapToInsertCourseResponse;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    SaveCourseService saveCourseService;
    @Autowired
    InactiveCourseService inactiveCourseService;
    @Autowired
    FindAllCourseService findAllCourseService;

    @PostMapping
    ResponseEntity<InsertCourseResponse> saveCourse(@RequestBody @Valid InsertCourseRequest requestDTO) {
        var course = mapToCourse(requestDTO);
        course = saveCourseService.execute(course);
        var responseDTO = mapToInsertCourseResponse(course);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(responseDTO.code()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @PutMapping("/inactivation/{code}")
    ResponseEntity<InsertCourseResponse> inactive(@PathVariable String code) {
        var course = inactiveCourseService.execute(code);
        var responseDTO = mapToInsertCourseResponse(course);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(responseDTO.code()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<Page<FindAllCourseResponse>> getAllCoursesPaged(@RequestParam(name = "status", required = false) Status status,
                                                                          @PageableDefault Pageable pageable) {
        Page<Course> coursePage = findAllCourseService.execute(status, pageable);
        Page<FindAllCourseResponse> responsePage = coursePage.map(CourseMapper::mapToFindAllCourseResponse);
        return ResponseEntity.ok(responsePage);
    }
}
