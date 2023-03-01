package com.example.finalproject.controller;

import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.service.CityService;
import com.example.finalproject.service.MyUserService;
import com.example.finalproject.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {
    private final MyUserService myUserService;
    private final CityService cityService;
    private final ServiceTypeService serviceTypeService;
    @GetMapping()
    public ResponseEntity<MyUser> register(@AuthenticationPrincipal MyUser user){
        MyUser myUser = myUserService.getUser(user.getId());
        return ResponseEntity.status(200).body(myUser);
    }

    @GetMapping("city")
    public ResponseEntity<List<City>> getAll( ){
        return ResponseEntity.status(200).body(cityService.getCites());
    }

    @GetMapping("service_type")
    public ResponseEntity<List<ServiceType>> getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(serviceTypeService.getAll());
    }

//    @PostMapping("/login")
//    public ResponseEntity<ApiResponseWithUser> login(@RequestBody @Valid LoginDTO loginDTO){
//        return ResponseEntity.status(200).body(myUserService.login(loginDTO));
//    }
}
