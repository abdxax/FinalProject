package com.example.finalproject.service;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.repestory.AuthRepstory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserSerivce {
    private  final AuthRepstory authRepstory;

    public void register(MyUser user){
        String password=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        authRepstory.save(user);
    }
}
