package com.example.finalproject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDTO {
    private Integer idUser;
    private Integer cityid;
    private String name;
    private String phone;

   // private Integer capcity;

}
