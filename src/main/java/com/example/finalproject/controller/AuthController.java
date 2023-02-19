package com.example.finalproject.controller;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.MyUserDetaiLsService;
import com.example.finalproject.service.MyUserSerivce;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final MyUserSerivce myUserSerivce;
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid MyUser user){
        myUserSerivce.register(user);
        return ResponseEntity.status(200).body("Register is done");
    }
    @PostMapping("/login")
    public ResponseEntity login(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body("Welcome "+user.getName());
    }
}
