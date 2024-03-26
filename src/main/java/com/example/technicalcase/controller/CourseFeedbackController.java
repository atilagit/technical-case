package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.InsertCourseFeedbackRequest;
import com.example.technicalcase.controller.data.responses.FindAllCourseNpsResponse;
import com.example.technicalcase.controller.data.responses.InsertCourseFeedbackResponse;
import com.example.technicalcase.controller.mappers.FeedbackMapper;
import com.example.technicalcase.entities.projections.CourseProjection;
import com.example.technicalcase.services.CourseFeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.technicalcase.controller.mappers.FeedbackMapper.mapToEntity;
import static com.example.technicalcase.controller.mappers.FeedbackMapper.mapToInsertFeedbackResponse;


@RestController
@RequestMapping("/course-feedbacks")
public class CourseFeedbackController {

    @Autowired
    CourseFeedbackService service;

    @PostMapping
    ResponseEntity<InsertCourseFeedbackResponse> saveFeedback(@RequestBody @Valid InsertCourseFeedbackRequest requestDTO) {
        var feedback = mapToEntity(requestDTO);
        feedback = service.save(feedback);
        var responseDTO = mapToInsertFeedbackResponse(feedback);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping("/reports/nps")
    ResponseEntity<List<FindAllCourseNpsResponse>> findCoursesNps() {
        List<CourseProjection> coursesNps = service.findCoursesNps();
        List<FindAllCourseNpsResponse> responses = coursesNps.stream().map(FeedbackMapper::mapToFindAllCourseNpsResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }
}