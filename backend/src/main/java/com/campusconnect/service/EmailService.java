package com.campusconnect.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendWelcomeEmail(String toEmail, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Welcome to CampusConnect!");
        message.setText("Hello " + name + ",\n\nWelcome to CampusConnect! We're excited to have you on board. You have successfully signed up. \n\nBest regards,\nCampusConnect Team");
        
        mailSender.send(message);
    }
}
