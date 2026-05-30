package com.example.customer_support_agent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import com.example.customer_support_agent.dto.SupportRequest;
import com.example.customer_support_agent.service.SupportAgentService;

@RestController
@RequestMapping("/api/support")
@RequiredArgsConstructor
public class SupportController {


    // add comment to explain the purpose of this field
    private final SupportAgentService supportAgentService;

    @PostMapping
    public String support(@RequestBody SupportRequest request) {
        return supportAgentService.processMessage(request.getMessage());
    }
}