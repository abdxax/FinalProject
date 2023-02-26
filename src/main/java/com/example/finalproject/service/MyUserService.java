package com.example.finalproject.service;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.dto.LoginDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repestory.AuthRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {
    private  final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService ;

    public ApiResponse register(MyUser user){
        String password=new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(password);
        authRepository.save(user);
        String token = jwtService.generatToken(user);
        return new ApiResponse(token,user);

    }

    public ApiResponse login(LoginDTO loginDTO){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        MyUser user=authRepository.findByEmail(loginDTO.getEmail());
        if(user==null){
            return new ApiResponse("The user name or password is not correct ",null);
        }

        String token= jwtService.generatToken(user);
        return new ApiResponse(token,user);
        //return ApiResponse.builder()
    }
}
