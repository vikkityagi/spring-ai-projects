package com.example.Enterprise.AI.Code.Review.Assistant.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.Enterprise.AI.Code.Review.Assistant.dto.CodeReviewRequestDto;
import com.example.Enterprise.AI.Code.Review.Assistant.service.ReviewService;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService codeReviewService;

    @PostMapping
    public String review(@RequestBody String request) {
        return codeReviewService.reviewCode(request);
    }
}