package com.example.finalproject.controller.user;

import com.example.finalproject.dto.ProfileDTO;
import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Profile;
import com.example.finalproject.service.CityService;
import com.example.finalproject.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/user/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final CityService cityService;
    @GetMapping("/getProfile")
    public ResponseEntity getProfile(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(profileService.getprofile(user.getId()));
    }
    @PostMapping("/addprofile")
    public ResponseEntity addProfile(@AuthenticationPrincipal MyUser user,@RequestBody @Valid ProfileDTO profileDTO){
        Profile userprofile=profileService.getprofile(user.getId());
        City city=cityService.getCity(profileDTO.getCityid());
        if(userprofile!=null){
            return ResponseEntity.status(400).body("The user has profile already");
        }
        if(city==null){
            return ResponseEntity.status(200).body("The id City id is not correct ");
        }

        profileService.addProfile(user,profileDTO);
        return ResponseEntity.status(200).body("The Profile Added done");
    }


}
