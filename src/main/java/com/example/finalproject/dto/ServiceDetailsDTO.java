package com.example.finalproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceDetailsDTO {

    @NotNull(message = "Description can not be empty")
    @Size(min = 10, message = "Description must contain at least 10 characters")
    private String description;
    @Positive(message = "service type id must be positive integer")
    private Integer serviceTypeId;
}
