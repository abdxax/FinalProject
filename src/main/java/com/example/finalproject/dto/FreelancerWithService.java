package com.example.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FreelancerWithService {
    @PositiveOrZero(message = "Capacity must be positive integer or zero")
    private Integer capacity;

    @NotNull(message = "Message can not be null")
    @Size(min = 10, max = 200, message = "Message must be between 10 and 200 characters")
    private String message;
    @NotNull(message = "Description can not be empty")
    @Size(min = 10, message = "Description must contain at least 10 characters")
    private String description;
    @Positive(message = "service type id must be positive integer")
    private Integer serviceTypeId;
}
