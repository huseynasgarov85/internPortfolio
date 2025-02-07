package com.example.internportfoliotask2.model.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterPayload {
    @NotBlank
    String name;
    @NotBlank
    String surname;
    @NotBlank
    String phone;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Please enter a valid email address")
    String email;
    @NotBlank
    String userName;
    @NotBlank
    String password;
    @NotBlank
    String rePassword;

}
