package com.example.finalproject.Advice;


import com.example.finalproject.ApiResponse;
import com.example.finalproject.handling.ApiException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.sql.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class AdviceController {


    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity methodArgumentTypeMismatch(MethodArgumentTypeMismatchException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity myApi(ApiException e){
        return ResponseEntity.status(e.getStatus()).body(new ApiResponse(e.getMessage(),null));
    }

    @ExceptionHandler(value = SQLIntegrityConstraintViolationException.class)
    public ResponseEntity dataIntegrityViolation(SQLIntegrityConstraintViolationException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity dataIntegrityViolation(MethodArgumentNotValidException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getFieldError().getDefaultMessage(),null));
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadable(HttpMessageNotReadableException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }

    @ExceptionHandler(value = JpaObjectRetrievalFailureException.class)
    public ResponseEntity JpaObjectRetrievalFailure(JpaObjectRetrievalFailureException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity JpaObjectRetrievalFailure(HttpRequestMethodNotSupportedException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolation(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity UsernameNotFound(UsernameNotFoundException e){
        return ResponseEntity.status(400).body(new ApiResponse(e.getMessage(),null));
    }



}
