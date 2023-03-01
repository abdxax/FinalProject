package com.example.finalproject.controller.user;

import com.example.finalproject.ApiResponseWithUser;
import com.example.finalproject.dto.FreelancerDTO;
import com.example.finalproject.dto.FreelancerWithService;
import com.example.finalproject.model.Freelancer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.FreelancerService;
import com.example.finalproject.service.ProfileService;
import com.example.finalproject.service.ServiceDetailsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user/freelancer")
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;
    private final ProfileService profileService;
    private final ServiceDetailsService serviceDetailsService;

    @GetMapping("/getFreelancer")
    public ResponseEntity<Freelancer> getFreelancer(@AuthenticationPrincipal MyUser user) {
        Freelancer freelancer = freelancerService.getFreelancer(user.getId());
        return ResponseEntity.status(200).body(freelancer);
    }

    @PostMapping("/addFreelancer")
    public ResponseEntity addFreelancer(@AuthenticationPrincipal MyUser user, @RequestBody @Valid FreelancerDTO freelancerDTO) {
        //Freelancer freelancer=freelancerRepostioty.
        freelancerService.addFreelancer(user, freelancerDTO);
        return ResponseEntity.status(200).body("Added");

    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@AuthenticationPrincipal MyUser user, @RequestBody @Valid FreelancerDTO freelancerDTO) {
        Boolean res = freelancerService.update(user, freelancerDTO);
        if (!res) {
            ResponseEntity.status(400).body("ERROR");
        }
        return ResponseEntity.status(200).body("Update done");
    }

    @PostMapping("/addFreelancerWithService")
    public ResponseEntity<ApiResponseWithUser> addResp(@AuthenticationPrincipal MyUser user, @RequestBody @Valid FreelancerWithService freelancer) {
        freelancerService.addFreelancerWithServoce(freelancer, user);
        return ResponseEntity.status(200).body(new ApiResponseWithUser("Done", user));
    }

    @GetMapping("get/by/service/type/{serviceTypeId}")
    public ResponseEntity getByServiceType(@PathVariable Integer serviceTypeId){
        return ResponseEntity.status(HttpStatus.OK).body(freelancerService.getByServiceType(serviceTypeId));
    }


}
