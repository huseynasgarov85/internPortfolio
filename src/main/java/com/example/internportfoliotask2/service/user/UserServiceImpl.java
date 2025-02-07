package com.example.internportfoliotask2.service.user;

import com.example.internportfoliotask2.globalException.exceptions.NotFoundException;
import com.example.internportfoliotask2.mapper.UserMapper;
import com.example.internportfoliotask2.model.entity.postgre.User;
import com.example.internportfoliotask2.model.payload.UserPayload;
import com.example.internportfoliotask2.model.response.UserResponse;
import com.example.internportfoliotask2.repo.postgre.UserRepo;
import com.example.internportfoliotask2.util.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final JwtTokenUtil jwtUtil;
    private final UserMapper userMapper;

    @Override
    public boolean checkEmail(String email) {
        return userRepo.existsByEmail(email);
    }

    @Override
    public User userSave(User user) {
        return userRepo.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.findUserByEmail(email).orElseThrow(() -> new NotFoundException("user not founded"));
    }

    private void validateUserId(Long id, String token) {
        String jwtToken = jwtUtil.extractTokenFromHeader(token);
        User user = jwtUtil.getUserById(jwtToken);
        if (user.getId() == null) {
            throw new RuntimeException("Token is invalid");
        }
        if (!user.getId().equals(id)) {
            throw new RuntimeException("Access denied: You can only access your own data");
        }
    }


    @Override
    public UserResponse getUserById(Long id, String token) {
        log.info("ActionLog started " + id);
        validateUserId(id, token);
        User user = userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not founded"));
        log.info("ActionLog end " + user.getEmail());
        return userMapper.mapToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserPayload request, String token) {
        User user = jwtUtil.getUserById(token);
        log.info("ActionLog started " + user);
        User user1 = userRepo.findById(user.getId()).orElseThrow(() -> new NotFoundException("userId not founded"));
        User afterMapUser = userMapper.mapFromRequestToUser(request, user1);
        userRepo.save(afterMapUser);
        log.info("ActionLog end " + afterMapUser.getEmail());
        return userMapper.mapToUserResponse(afterMapUser);
    }

    @Override
    public User findUserByUserName(String username) {
        return userRepo.findUserByUserName(username).orElseThrow();
    }

    @Override
    public void deleteUser(Long id, String token) {
        validateUserId(id, token);
        userRepo.deleteById(id);
    }
}
