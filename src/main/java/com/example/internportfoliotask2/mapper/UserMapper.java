package com.example.internportfoliotask2.mapper;

import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.UserPayload;
import com.example.internportfoliotask2.model.response.UserResponse;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .userName(user.getName())
                .build();
    }

    public User mapFromRequestToUser(UserPayload userPayload, User user) {
        return User.builder()
                .email(userPayload.getEmail())
                .password(userPayload.getPassword())
                .userName(userPayload.getUserName())
                .build();

    }
}
