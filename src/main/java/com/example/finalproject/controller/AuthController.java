package com.example.finalproject.controller;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.dto.LoginDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/",allowCredentials = "true")
public class AuthController {
    private final MyUserService myUserService;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser user){
        myUserService.register(user);
        return ResponseEntity.status(200).body("Register is done");
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginDTO loginDTO){
        return ResponseEntity.status(200).body(myUserService.login(loginDTO));
    }

}

