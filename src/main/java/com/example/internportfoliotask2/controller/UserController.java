package com.example.internportfoliotask2.controller;

import com.example.internportfoliotask2.model.payload.UserPayload;
import com.example.internportfoliotask2.model.response.UserResponse;
import com.example.internportfoliotask2.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id,
                                                    @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(userService.getUserById(id, token));
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UserPayload userPayload,
                                                   @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {


        return ResponseEntity.ok(userService.updateUser(userPayload, token));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id,
                                           @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        userService.deleteUser(id, token);
        return ResponseEntity.noContent().build();
    }
}
