package com.example.internportfoliotask2.repo.mongo;

import com.example.internportfoliotask2.model.entity.mongo.Token;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TokenRepo extends MongoRepository<Token, String> {
    void deleteTokenByAccessToken(String token);

}
