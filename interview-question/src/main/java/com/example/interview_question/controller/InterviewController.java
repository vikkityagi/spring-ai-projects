package com.example.interview_question.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.interview_question.dto.InterviewRequest;
import com.example.interview_question.service.InteviewService;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
public class InterviewController {

    private final InteviewService interviewService;

    @PostMapping
    public String generateQuestions(
            @RequestBody InterviewRequest request) {

        return interviewService.generateQuestions(request);
    }
}