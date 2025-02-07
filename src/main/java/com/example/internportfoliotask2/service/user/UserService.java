package com.example.internportfoliotask2.service.user;


import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.UserPayload;
import com.example.internportfoliotask2.model.response.UserResponse;

public interface UserService {

    boolean checkEmail(String email);
    User userSave(User user);
    User findUserByEmail(String email);
    UserResponse getUserById(Long id, String token);
    UserResponse updateUser( UserPayload request, String token);
    User findUserByUserName(String username);
    void deleteUser(Long id, String token);

}
