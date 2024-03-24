package com.example.technicalcase.adapters;

import org.springframework.stereotype.Service;

@Service
public class MockEmailSender implements EmailSender {
    @Override
    public void send(String recipientEmail, String subject, String body) {
        System.out.printf("Simulating sending email to [%s]:\n%n", recipientEmail);
        System.out.printf("""
                Subject: %s
                Body: %s
                %n""", subject, body);
    }
}