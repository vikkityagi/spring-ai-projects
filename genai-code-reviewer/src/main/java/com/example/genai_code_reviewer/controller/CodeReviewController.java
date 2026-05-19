package com.example.genai_code_reviewer.controller;

import com.example.genai_code_reviewer.service.CodeReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class CodeReviewController {

    private final CodeReviewService codeReviewService;

    @PostMapping
    public String review(@RequestBody String code) {
        return codeReviewService.reviewCode(code);
    }
}