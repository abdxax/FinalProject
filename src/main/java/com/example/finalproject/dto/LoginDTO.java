package com.example.finalproject.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class LoginDTO {
    @Email
    private String email;
    private String password;

}
