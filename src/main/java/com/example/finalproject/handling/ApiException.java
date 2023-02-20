package com.example.finalproject.handling;

import lombok.Data;

@Data
public class ApiException extends RuntimeException {
    int status;
    public ApiException(String msg,int status){
        super(msg);
        this.status=status;
    }
}
