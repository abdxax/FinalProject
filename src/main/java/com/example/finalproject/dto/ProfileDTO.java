package com.example.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDTO {
    @Positive(message = "City id must be positive integer")
    private Integer cityId;
    @NotNull(message = "Phone can not be empty")
    @Size(min = 9, max = 13, message = "Phone must be between 9 and 13 numbers")
    private String phone;

   // private Integer capcity;

}
