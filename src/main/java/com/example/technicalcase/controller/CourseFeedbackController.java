package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.InsertCourseFeedbackRequest;
import com.example.technicalcase.controller.data.responses.FindAllCourseNpsResponse;
import com.example.technicalcase.controller.data.responses.InsertCourseFeedbackResponse;
import com.example.technicalcase.controller.mappers.FeedbackMapper;
import com.example.technicalcase.entities.projections.CourseProjection;
import com.example.technicalcase.services.FindCoursesNpsService;
import com.example.technicalcase.services.SaveCourseFeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.technicalcase.controller.mappers.FeedbackMapper.mapToEntity;
import static com.example.technicalcase.controller.mappers.FeedbackMapper.mapToInsertFeedbackResponse;


@RestController
@RequestMapping("/course-feedbacks")
public class CourseFeedbackController {

    @Autowired
    SaveCourseFeedbackService saveCourseFeedbackService;

    @Autowired
    FindCoursesNpsService findCoursesNpsService;

    @PostMapping
    ResponseEntity<InsertCourseFeedbackResponse> saveFeedback(@RequestBody @Valid InsertCourseFeedbackRequest requestDTO) {
        var feedback = mapToEntity(requestDTO);
        feedback = saveCourseFeedbackService.execute(feedback);
        var responseDTO = mapToInsertFeedbackResponse(feedback);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }

    @GetMapping("/reports/nps")
    ResponseEntity<Page<FindAllCourseNpsResponse>> findCoursesNps(@RequestParam(defaultValue = "4") Integer minEnrollments,
                                                                  @PageableDefault(sort = "nps", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<CourseProjection> coursesNps = findCoursesNpsService.execute(minEnrollments, pageable);
        Page<FindAllCourseNpsResponse> responsePage = coursesNps.map(FeedbackMapper::mapToFindAllCourseNpsResponse);
        return ResponseEntity.ok(responsePage);
    }
}