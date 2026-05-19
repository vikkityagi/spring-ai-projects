package com.example.genai_code_reviewer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CodeReviewService {

    private final ChatClient chatClient;

    public String reviewCode(String code) {

        String prompt = """
                Act as a professional senior software code reviewer.

                Analyze the provided code carefully.

                Return the response ONLY in the following exact format:

                ==========================================
                CODE REVIEW REPORT
                ==========================================

                BUGS:
                - Mention all bugs clearly
                - If no bugs found, write: No major bugs found

                SECURITY ISSUES:
                - Mention security vulnerabilities
                - If none, write: No security issues found

                PERFORMANCE ISSUES:
                - Mention performance problems
                - If none, write: No performance issues found

                CLEAN CODE SUGGESTIONS:
                - Mention naming, structure, readability improvements
                - If none, write: Code is clean

                FINAL RATING:
                X/10

                SUMMARY:
                Short overall conclusion.

                IMPORTANT RULES:
                - Return plain text only
                - Keep formatting clean
                - Do not return JSON
                - Do not add extra explanation outside this format

                CODE TO REVIEW:
                """ + code;

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}