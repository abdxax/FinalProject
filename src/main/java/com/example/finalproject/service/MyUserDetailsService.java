package com.example.finalproject.service;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.repestory.AuthRepstory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final AuthRepstory authRepstory;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser user=authRepstory.findByEmail(username);
        if(user==null){
            throw  new UsernameNotFoundException("The Email or Password is not correct");
        }

        return user;
    }


}
