package com.example.internportfoliotask2.model.proporties;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtData {

    String publicKey;
    String privateKey;
    Integer accessTokenValidityTime;
    Integer refreshTokenValidityTime;

    public Long getRefreshTokenValidityTime(Integer rememberMe) {
        return refreshTokenValidityTime * (rememberMe == 1 ? 30L : 1L);
    }

}