package com.example.technicalcase.controller;

import com.example.technicalcase.controller.data.requests.InsertFeedbackRequest;
import com.example.technicalcase.controller.data.responses.InsertFeedbackResponse;
import com.example.technicalcase.services.FeedbackService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static com.example.technicalcase.controller.mappers.FeedbackMapper.mapToEntity;
import static com.example.technicalcase.controller.mappers.FeedbackMapper.mapToInsertFeedbackResponse;


@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    @Autowired
    FeedbackService service;

    @PostMapping
    ResponseEntity<InsertFeedbackResponse> saveFeedback(@RequestBody @Valid InsertFeedbackRequest requestDTO) {
        var feedback = mapToEntity(requestDTO);
        feedback = service.save(feedback);
        var responseDTO = mapToInsertFeedbackResponse(feedback);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.id()).toUri();
        return ResponseEntity.created(uri).body(responseDTO);
    }
}