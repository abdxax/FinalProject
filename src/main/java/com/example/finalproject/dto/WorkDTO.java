package com.example.finalproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class WorkDTO {
    @NotNull(message = "Title can not be empty")
    @Size(min = 3, max = 255,message = "Title must be between 3 to 255 characters")
    private String title;
    @NotNull(message = "Description can not be empty")
    @Min(value = 10, message = "Description must contain at least 10 characters")
    private String description;
    @Positive(message = "service detail id must be positive integer")
    private Integer serviceDetailId;
}
