package com.example.image_generator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.image_generator.dto.AnswerRequest;
import com.example.image_generator.dto.StartInterviewRequest;
import com.example.image_generator.service.InterviewService;

@RestController
@RequestMapping("/api/interview")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping("/start")
    public String start(@RequestBody StartInterviewRequest request) {
        return interviewService.startInterview(request);
    }

    @PostMapping("/answer")
    public String answer(@RequestBody AnswerRequest request) {
        return interviewService.answerQuestion(request.getAnswer());
    }

    @PostMapping("/end")
    public String end() {
        return interviewService.endInterview();
    }
}