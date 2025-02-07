package com.example.internportfoliotask2.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {

    public String generateOtpCode() {
        Random random = new Random();
        int otpCode = 100000 + random.nextInt(900000);
        return Integer.toString(otpCode);
    }
}
