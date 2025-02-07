package com.example.internportfoliotask2.service.otp;

import com.example.internportfoliotask2.globalException.exceptions.NotFoundException;
import com.example.internportfoliotask2.model.entity.mongo.Otp;
import com.example.internportfoliotask2.repo.mongo.OtpRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OtpServiceImpl implements OtpService {
    private final OtpRepo otpRepo;

    @Override
    public Otp findByEmail(String email) {
        return otpRepo.findOtpByEmail(email);
    }

    @Override
    public void save(Otp otp) {
        otpRepo.save(otp);
    }
}
