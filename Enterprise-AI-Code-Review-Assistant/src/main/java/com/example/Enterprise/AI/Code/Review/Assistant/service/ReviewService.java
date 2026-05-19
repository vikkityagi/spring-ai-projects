package com.example.Enterprise.AI.Code.Review.Assistant.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.Enterprise.AI.Code.Review.Assistant.dto.CodeReviewRequestDto;
import com.example.Enterprise.AI.Code.Review.Assistant.entity.CompanyDocument;
import com.example.Enterprise.AI.Code.Review.Assistant.repo.CompanyDocumentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ChatClient chatClient;
    private final CompanyDocumentRepository repository;

    public String reviewCode(String request) {

        List<CompanyDocument> docs = repository.findAll();

        if (docs.isEmpty()) {
            return "No coding standards uploaded.";
        }

        String standards = docs.stream()
                .map(CompanyDocument::getExtractedText)
                .reduce("", String::concat);

        String prompt = """
                Act as a strict enterprise code reviewer.

                Compare the provided code ONLY against the company coding standards below.

                Return exact format:

                =====================================
                ENTERPRISE CODE COMPLIANCE REPORT
                =====================================

                VIOLATIONS:
                - List violations clearly
                - Mention exact company rule

                SECURITY ISSUES:
                - Mention security issues

                CLEAN CODE ISSUES:
                - Mention readability/design issues

                COMPLIANCE SCORE:
                X%

                FINAL VERDICT:
                PASS or FAIL

                COMPANY STANDARDS:
                """ + standards +

                """

                CODE TO REVIEW:
                """ + request;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}