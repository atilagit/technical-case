package com.example.technicalcase.adapters;

public interface EmailSender {
    void send(String recipientEmail, String subject, String body);
}