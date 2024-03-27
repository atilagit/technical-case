package com.example.technicalcase.services;

import com.example.technicalcase.adapters.EmailSender;
import com.example.technicalcase.entities.CourseFeedback;
import com.example.technicalcase.observer.Observer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmailService implements Observer {

    @Autowired
    EmailSender emailSender;

    public void execute(String recipientEmail, String subject, String body) {
        emailSender.send(recipientEmail, subject, body);
    }

    @Override
    public void update(Object data) {
        if (data instanceof CourseFeedback courseFeedback && courseFeedback.getGrade() <= 6) {
            var course = courseFeedback.getCourse();
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
                    """.formatted(instructor.getName(), course.getName(), courseFeedback.getGrade(), courseFeedback.getReason());
            execute(recipientEmail, subject, body);
        }

    }
}