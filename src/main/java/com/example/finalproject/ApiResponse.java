package com.example.finalproject;

import com.example.finalproject.model.MyUser;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {
    private String message;
    private MyUser user=null;
}
