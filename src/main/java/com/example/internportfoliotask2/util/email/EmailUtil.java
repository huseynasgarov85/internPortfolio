package com.example.internportfoliotask2.util.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EmailUtil {
    private final JavaMailSender javaMailSender;

    public void sendToEmailForRegister(String otp, String email) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setText("otp code is " + otp);
            mimeMessageHelper.setSubject("Otp verification");
            mimeMessageHelper.setTo(email);
        } catch (MessagingException e) {
            log.error("unexpected error " + e.getMessage());
            throw new RuntimeException("gave an error");
        }
        javaMailSender.send(mimeMessage);
    }

}
