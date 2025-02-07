package com.example.internportfoliotask2.model.entity.mongo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "otp")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Otp {
    @Id
    String id;
    String email;
    String otp;
    LocalDateTime createdAt;
}
