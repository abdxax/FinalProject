package com.example.finalproject.dto;

import com.example.finalproject.model.ServiceType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FreelancerDTO {
    private Integer capacity;

    private List<Integer> serviceTypeList;
}
