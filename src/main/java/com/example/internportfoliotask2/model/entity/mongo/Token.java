package com.example.internportfoliotask2.model.entity.mongo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("tokens")
public class Token implements Serializable {
    @Id
    private String id;
    private String email;
    private String accessToken;
    private String refreshToken;

}