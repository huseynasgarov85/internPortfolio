package com.example.internportfoliotask2.controller;

import com.example.internportfoliotask2.model.payload.LoginPayload;
import com.example.internportfoliotask2.model.payload.OtpPayload;
import com.example.internportfoliotask2.model.payload.RegisterPayload;
import com.example.internportfoliotask2.model.response.LoginResponse;
import com.example.internportfoliotask2.model.response.RegisterResponse;
import com.example.internportfoliotask2.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class Auth {
    private final AuthService authService;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody @Valid RegisterPayload registerPayload) {
        return authService.register(registerPayload);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<?> verifyOtp(@RequestBody @Valid OtpPayload otpPayload) {
        return authService.verifyOtp(otpPayload);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody @Valid LoginPayload loginPayload) {
        return authService.login(loginPayload);
    }

}
