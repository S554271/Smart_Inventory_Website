package com.example.inventorySystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.inventorySystem.entity.User;
import com.example.inventorySystem.repository.UserRepository;

@Service
public class UserAuthenticationService {

    private final JavaMailSender mailSender;
    private final UserRepository userRepository; // Assuming you have a UserRepository

    @Autowired
    public UserAuthenticationService(JavaMailSender mailSender, UserRepository userRepository) {
        this.mailSender = mailSender;
        this.userRepository = userRepository;
    }

    public void requestPasswordReset(String email) throws Exception {
        User user = userRepository.findByEmail(email); // Check if the user exists
        if (user == null) {
            throw new Exception("User not found");
        }

        // Create a password reset link (you should generate a unique token and link)
        String resetLink = "http://localhost:8080/change-password";

        // Send email
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Password Reset Request");
        message.setText("Click the link to reset your password: " + resetLink);
        message.setFrom("anumulajayanthms@gmail.com"); // Set the From header
        mailSender.send(message);
    }
}
