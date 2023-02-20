package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServiceDetailsDTO {
    private Integer userId;
    private Integer freelancerId;
    private String title;
    private String description;
    private Integer serviceTypeId;
}
