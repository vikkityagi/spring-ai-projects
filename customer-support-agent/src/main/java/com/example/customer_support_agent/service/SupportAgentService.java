package com.example.customer_support_agent.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import com.example.customer_support_agent.tools.SupportTools;

@Service
@RequiredArgsConstructor
public class SupportAgentService {

    private final ChatClient chatClient;
    private final SupportTools supportTools;

    public String processMessage(String userMessage) {

        String lowerMessage = userMessage.toLowerCase();

        if (lowerMessage.contains("order")) {

            String orderId = extractOrderId(userMessage);

            String orderStatus = supportTools.getOrderStatus(orderId);

            return chatClient.prompt()
                    .user("""
                            User asked: %s

                            Tool result:
                            %s

                            Respond professionally as customer support agent.
                            """
                            .formatted(userMessage, orderStatus))
                    .call()
                    .content();
        }

        if (lowerMessage.contains("refund")) {

            String orderId = extractOrderId(userMessage);

            String refundResult =
                    supportTools.checkRefundEligibility(orderId);

            return chatClient.prompt()
                    .user("""
                            User asked: %s

                            Tool result:
                            %s

                            Respond professionally as customer support agent.
                            """
                            .formatted(userMessage, refundResult))
                    .call()
                    .content();
        }

        if (lowerMessage.contains("ticket")) {

            String ticketResult =
                    supportTools.createSupportTicket(userMessage);

            return chatClient.prompt()
                    .user("""
                            User asked: %s

                            Tool result:
                            %s

                            Respond professionally as customer support agent.
                            """
                            .formatted(userMessage, ticketResult))
                    .call()
                    .content();
        }

        return chatClient.prompt()
                .user("""
                        Act as professional customer support agent.

                        Answer user query:

                        %s
                        """
                        .formatted(userMessage))
                .call()
                .content();
    }

    private String extractOrderId(String message) {

        String digits = message.replaceAll("\\D+", "");

        if (digits.isEmpty()) {
            return "0";
        }

        return digits;
    }
}
