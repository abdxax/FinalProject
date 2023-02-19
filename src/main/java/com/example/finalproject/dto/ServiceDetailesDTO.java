package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceDetailesDTO {
    private Integer userId;
    private Integer freelancerId;
    private String title;
    private String description;
    private Integer serviecTypeId;
}
