package com.example.internportfoliotask2.service.otp;

import com.example.internportfoliotask2.model.entity.mongo.Otp;

public interface OtpService {

    Otp findByEmail(String email);
    void save(Otp otp);
}
