package com.example.internportfoliotask2.repo.mongo;

import com.example.internportfoliotask2.model.entity.mongo.Otp;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OtpRepo extends MongoRepository<Otp, String> {

    Otp findOtpByEmail(String email);
}
