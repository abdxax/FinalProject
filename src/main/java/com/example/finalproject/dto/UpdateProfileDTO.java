package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProfileDTO {
    private String name;
    private String phone;
    private Integer cityId;

    private Integer userId;
}
