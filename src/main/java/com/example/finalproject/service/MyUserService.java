package com.example.finalproject.service;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.repestory.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private  final AuthRepository authRepository;

    public void register(MyUser user){
        String password=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        authRepository.save(user);
    }
}
