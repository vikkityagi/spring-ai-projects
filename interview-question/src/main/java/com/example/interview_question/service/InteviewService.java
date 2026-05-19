package com.example.interview_question.service;




import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.interview_question.dto.InterviewRequest;

@Service
@RequiredArgsConstructor
public class InteviewService {

    private final ChatClient chatClient;

    public String generateQuestions(InterviewRequest request) {

        String prompt = """
                Act as a senior technical interviewer.

                Generate interview questions based on:

                Technology: %s
                Experience: %s
                Difficulty Level: %s

                Return response in this format:

                ====================================
                INTERVIEW QUESTIONS
                ====================================

                QUESTION 1:
                Question here

                ANSWER:
                Answer here

                FOLLOW-UP:
                Follow-up question here

                ====================================

                Generate 5 questions.

                Keep answers concise and interview-oriented.
                """
                .formatted(
                        request.getTechnology(),
                        request.getExperience(),
                        request.getDifficulty()
                );

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}