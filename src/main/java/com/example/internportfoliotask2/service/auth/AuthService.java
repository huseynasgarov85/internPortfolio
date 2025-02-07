package com.example.internportfoliotask2.service.auth;

import com.example.internportfoliotask2.model.payload.LoginPayload;
import com.example.internportfoliotask2.model.payload.OtpPayload;
import com.example.internportfoliotask2.model.payload.RegisterPayload;
import com.example.internportfoliotask2.model.response.LoginResponse;
import com.example.internportfoliotask2.model.response.RegisterResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    RegisterResponse register(RegisterPayload registerPayload);
    ResponseEntity<?> verifyOtp(OtpPayload otpPayload);
    LoginResponse login(LoginPayload loginPayload);
}
