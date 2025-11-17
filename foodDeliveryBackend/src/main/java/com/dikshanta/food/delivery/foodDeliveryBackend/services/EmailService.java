package com.dikshanta.food.delivery.foodDeliveryBackend.services;

import com.dikshanta.food.delivery.foodDeliveryBackend.exceptions.FailedToSendMailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String to, String from, String subject, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(to);
            helper.setFrom("dikshantaacharya04@gmail.com");
            helper.setSubject(subject);
            helper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new FailedToSendMailException("Failed to sent an email");
        }
    }
}
