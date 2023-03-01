package com.example.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateProfileDTO {
    @NotNull(message = "Name can not be null")
    @Size(min = 3, max = 200, message = "Name must be between 3 and 200 characters")
    private String name;
    @NotNull(message = "Phone can not be empty")
    @Size(min = 9, max = 13, message = "Phone must be between 9 and 13 numbers")
    private String phone;
    @Positive
    private Integer cityId;
}
