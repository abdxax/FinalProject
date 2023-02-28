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
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final MyUserService myUserService;
    @GetMapping()
    public ResponseEntity<MyUser> register(@AuthenticationPrincipal MyUser user){
        MyUser myUser = myUserService.getUser(user.getId());
        return ResponseEntity.status(200).body(myUser);
    }
//    @PostMapping("/login")
//    public ResponseEntity<ApiResponse> login(@RequestBody @Valid LoginDTO loginDTO){
//        return ResponseEntity.status(200).body(myUserService.login(loginDTO));
//    }
}
