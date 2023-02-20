package com.example.finalproject.service;

import com.example.finalproject.dto.FreelancerDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.Freelancer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Profile;
import com.example.finalproject.model.Project;
import com.example.finalproject.repestory.FreelancerRepostioty;
import com.example.finalproject.repestory.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FreelancerService {
    private final FreelancerRepostioty freelancerRepostioty;
    private final ProfileRepository profileRepository;
    public void addFreelancer(MyUser user, FreelancerDTO freelancerDTO){
        Profile profile=profileRepository.findByIdEquals(freelancerDTO.getProfileId());
        if(profile==null||profile.getUserId()!=user.getId()){
            throw new ApiException("");
        }
        Freelancer freelancer=new Freelancer(null,freelancerDTO.getCapcity(),freelancerDTO.getServiceTypeList(),profile);
        freelancerRepostioty.save(freelancer);

    }
}
