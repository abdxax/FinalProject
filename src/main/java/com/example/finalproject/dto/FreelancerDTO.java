package com.example.finalproject.dto;

import com.example.finalproject.model.ServiceType;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FreelancerDTO {
    @PositiveOrZero(message = "Capacity must be positive integer or zero")
    private Integer capacity;

    @NotNull(message = "Message can not be null")
    @Size(min = 10, max = 200, message = "Message must be between 10 and 200 characters")
    private String message;
    @NotNull(message = "Service types must be selected")
    private List<Integer> serviceTypeList;

}
