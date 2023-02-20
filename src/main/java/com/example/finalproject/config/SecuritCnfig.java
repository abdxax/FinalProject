package com.example.finalproject.config;

import com.example.finalproject.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecuritCnfig {
    private final MyUserDetailsService myUserDetaiLsService;
@Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider=new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(myUserDetaiLsService);
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        return daoAuthenticationProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
    http.csrf().disable()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            .and()
            .authenticationProvider(authenticationProvider())
            .authorizeHttpRequests()
            .requestMatchers("/api/v1/auth/register").permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .logout()
            .logoutUrl("/api/v1/auth/logout")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .and()
            .httpBasic()

            ;
    return http.build();
    }

}
