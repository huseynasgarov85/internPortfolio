package com.example.internportfoliotask2.model.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginPayload {
    @NotBlank
    String userName;
    @NotBlank
    String password;
}
