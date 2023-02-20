package com.example.finalproject.controller.user;

import com.example.finalproject.dto.FreelancerDTO;
import com.example.finalproject.model.Freelancer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repestory.FreelancerRepostioty;
import com.example.finalproject.service.FreelancerService;
import com.example.finalproject.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/freelancer")
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;
    private final ProfileService profileService;
@PostMapping("/addFreelancer")
    public ResponseEntity addFreelancer(@AuthenticationPrincipal MyUser user, @RequestBody @Valid FreelancerDTO freelancerDTO){
       // Freelancer freelancer=freelancerRepostioty.
        freelancerService.addFreelancer(user,freelancerDTO);
        return ResponseEntity.status(200).body("Added");

    }
}
