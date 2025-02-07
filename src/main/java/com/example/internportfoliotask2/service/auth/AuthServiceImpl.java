package com.example.internportfoliotask2.service.auth;

import com.example.internportfoliotask2.globalException.exceptions.AlreadyExistsException;
import com.example.internportfoliotask2.globalException.exceptions.AuthenticationException;
import com.example.internportfoliotask2.globalException.exceptions.OtpInvalid;
import com.example.internportfoliotask2.model.entity.mongo.Otp;
import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.LoginPayload;
import com.example.internportfoliotask2.model.payload.OtpPayload;
import com.example.internportfoliotask2.model.payload.RegisterPayload;
import com.example.internportfoliotask2.model.response.LoginResponse;
import com.example.internportfoliotask2.model.response.RegisterResponse;
import com.example.internportfoliotask2.repo.postgre.UserRepo;
import com.example.internportfoliotask2.service.otp.OtpService;
import com.example.internportfoliotask2.service.security.SecurityService;
import com.example.internportfoliotask2.service.user.UserService;
import com.example.internportfoliotask2.util.email.EmailUtil;
import com.example.internportfoliotask2.util.otp.OtpUtil;
import com.example.internportfoliotask2.util.security.JwtTokenUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;
    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final OtpService otpService;

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepository;
    private final SecurityService userDetailService;
    private final JwtTokenUtil jwtTokenUtil;


    @Override
    public RegisterResponse register(RegisterPayload registerPayload) {
        log.info("ActionLog started register " + registerPayload.getEmail());
        if (userService.checkEmail(registerPayload.getEmail())) {
            throw new AlreadyExistsException("Email already registered");
        }
        User user = objectMapper.convertValue(registerPayload, User.class);
        user.setPassword(passwordEncoder.encode(registerPayload.getPassword()));
        user.setActive(false);
        String otp = otpUtil.generateOtpCode();
        emailUtil.sendToEmailForRegister(otp, user.getEmail());
        Otp otp1 = new Otp(new ObjectId().toString(), user.getEmail(), otp, LocalDateTime.now());
        otpService.save(otp1);
        log.info("ActionLog end register " + registerPayload.getEmail());
        return objectMapper.convertValue(userService.userSave(user), RegisterResponse.class);
    }

    @Override
    public ResponseEntity<?> verifyOtp(OtpPayload otpPayload) {
        log.info("ActonLog verfiyOtp started" + otpPayload.getOtp() + " " + otpPayload.getEmail());
        Otp otp = otpService.findByEmail(otpPayload.getEmail());
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(otp.getCreatedAt(), now);
        if (Objects.equals(otp.getOtp(), otpPayload.getOtp()) && duration.toMinutes() <= 2) {
            User user = userService.findUserByEmail(otp.getEmail());
            user.setActive(true);
            userService.userSave(user);
        } else {
            throw new OtpInvalid("otp is invalid");
        }
        log.info("ActonLog verfiyOtp end" + otpPayload.getOtp() + " " + otpPayload.getEmail());
        return ResponseEntity.ok("okey user was verify otp and already registered");
    }

    @Override
    public LoginResponse login(LoginPayload request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
//        );
//        User user = userService.findUserByEmail(request.getEmail());
//        String token = jwtUtil.generateToken(user.getEmail(), user.getId());


        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("incorrect username or password");
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getUserName());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new LoginResponse(jwt);
    }

}
