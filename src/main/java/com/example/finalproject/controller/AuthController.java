package com.example.finalproject.controller;

import com.example.finalproject.ApiResponseWithUser;
import com.example.finalproject.dto.LoginDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/",allowCredentials = "true")
public class AuthController {
    private final MyUserService myUserService;
    @PostMapping("/register")
    public ResponseEntity<ApiResponseWithUser> register(@RequestBody @Valid MyUser user){
        myUserService.register(user);
        return ResponseEntity.status(200).body(new ApiResponseWithUser("Register done",user));
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponseWithUser> login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.status(200).body(myUserService.login(loginDTO));
    }

}

