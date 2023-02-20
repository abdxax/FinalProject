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
        Freelancer f=freelancerRepostioty.findByProfile(profile);
        if(profile==null||profile.getUserId()!=user.getId()||f!=null){
            throw new ApiException("Some Thing Error");
        }
        Freelancer freelancer=new Freelancer(null,freelancerDTO.getCapcity(),freelancerDTO.getServiceTypeList(),profile);
        freelancerRepostioty.save(freelancer);

    }

    public Boolean update(Integer id,MyUser user,FreelancerDTO freelancer){
        Freelancer f=freelancerRepostioty.findByIdEquals(id);
        Profile profile=profileRepository.findByIdEquals(f.getProfile().getId());
        if(f==null||profile==null||profile.getUserId()!=user.getId()){
            return false;
        }
        f.setCapcity(freelancer.getCapcity());
        f.setServiceTypeList(freelancer.getServiceTypeList());
        freelancerRepostioty.save(f);
        return true;
    }

    public Freelancer getFreelancer(Integer id){
        Freelancer f= freelancerRepostioty.findByIdEquals(id);
        if(f==null){
            return  null;
        }
        return f;

    }
}
