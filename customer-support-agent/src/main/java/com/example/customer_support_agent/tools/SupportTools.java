package com.example.customer_support_agent.tools;


import org.springframework.stereotype.Component;

@Component
public class SupportTools {

    public String getOrderStatus(String orderId) {

        if ("123".equals(orderId)) {
            return "Order 123 is out for delivery.";
        }

        if ("456".equals(orderId)) {
            return "Order 456 has been delivered.";
        }

        return "Order not found.";
    }

    public String checkRefundEligibility(String orderId) {

        if ("456".equals(orderId)) {
            return "Order 456 is eligible for refund.";
        }

        return "Refund not eligible.";
    }

    public String createSupportTicket(String issue) {
        return "Support ticket created successfully. Ticket ID: TICKET-1001";
    }
}