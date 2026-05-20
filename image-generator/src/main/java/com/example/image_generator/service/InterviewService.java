package com.example.image_generator.service;



import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.image_generator.dto.StartInterviewRequest;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final ChatClient chatClient;

    private String conversationContext = "";

    public String startInterview(StartInterviewRequest request) {

        conversationContext = """
        You are a strict senior technical interviewer.

        Conduct ONLY a technical mock interview.

        Technology: %s
        Experience: %s

        STRICT RULES:
        1. Ask ONLY technical questions.
        2. Do NOT ask introduction/background questions.
        3. Start directly with technical Question 1.
        4. Ask one question at a time.
        5. Wait for candidate answer.
        6. Ask technical follow-up questions.
        7. Be strict like enterprise interviewer.
        8. Focus only on %s topics.

        Response format:

        QUESTION 1:
        <technical question>
        """
        .formatted(
                request.getTechnology(),
                request.getExperience(),
                request.getTechnology()
        );
        
        return chatClient.prompt()
                .user(conversationContext + "\nStart interview.")
                .call()
                .content();
    }

    public String answerQuestion(String answer) {

        conversationContext += "\nCandidate Answer: " + answer;

        String response = chatClient.prompt()
                .user(conversationContext +
                        "\nEvaluate answer and ask next question.")
                .call()
                .content();

        conversationContext += "\nInterviewer: " + response;

        return response;
    }

    public String endInterview() {

        return chatClient.prompt()
                .user(conversationContext + """
                        
                        End interview.
                        
                        Provide:
                        1. Technical score out of 10
                        2. Communication score
                        3. Weak areas
                        4. Strengths
                        5. Improvement plan
                        """)
                .call()
                .content();
    }
}