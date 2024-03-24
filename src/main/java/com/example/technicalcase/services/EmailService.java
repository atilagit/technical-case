package com.example.technicalcase.services;

import com.example.technicalcase.adapters.EmailSender;
import com.example.technicalcase.entities.Feedback;
import com.example.technicalcase.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements Observer {

    @Autowired
    EmailSender emailSender;

    public void sendEmail(String recipientEmail, String subject, String body) {
        emailSender.send(recipientEmail, subject, body);
    }

    @Override
    public void update(Object data) {
        if (data instanceof Feedback feedback && feedback.getGrade() <= 6) {
            var course = feedback.getCourse();
            var instructor = course.getInstructor();
            String recipientEmail = instructor.getEmail();
            String subject = "Feedback " + course.getName();
            String body = """
                    Olá %s!
                    Chegou um novo feedback sobre o seu curso %s. Segue abaixo os dados:
                    
                    Nota: %d
                    Motivo: %s
                    
                    Obs.:
                    Lembre-se, você só receberá notificações dos feedbacks com notas abaixo da média.
                    Isso não implica necessariamente que todas os feedbacks do(s) seu(s) curso estão abaixo da média.
                    """.formatted(instructor.getName(), course.getName(), feedback.getGrade(), feedback.getReason());
            sendEmail(recipientEmail, subject, body);
        }

    }
}