package com.example.finalproject.service;

import com.example.finalproject.dto.FreelancerDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.*;
import com.example.finalproject.repestory.FreelancerRepostioty;
import com.example.finalproject.repestory.ProfileRepository;
import com.example.finalproject.repestory.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreelancerService {
    private final FreelancerRepostioty freelancerRepostioty;
    private final ProfileRepository profileRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    public void addFreelancer(MyUser user, FreelancerDTO freelancerDTO){
        Profile profile=profileRepository.findByIdEquals(user.getId());
        if(profile==null){
            throw new ApiException("Profile not found. Complete profile first.",404);
        }
        Freelancer olfFreelancer = freelancerRepostioty.findByIdEquals(user.getId());
        if(olfFreelancer!=null){
            throw new ApiException("You already have freelancer profile!",400);
        }
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAllById(freelancerDTO.getServiceTypeList());

        Freelancer freelancer=new Freelancer(null,freelancerDTO.getCapacity(),serviceTypeList,profile);
        freelancerRepostioty.save(freelancer);
        for (ServiceType s: serviceTypeList){
            s.getFreelancer().add(freelancer);
            serviceTypeRepository.save(s);
        }

    }

    public Boolean update(MyUser user,FreelancerDTO freelancer){
        Freelancer oldFreelancer=freelancerRepostioty.findByIdEquals(user.getId());
        if(oldFreelancer==null){
            throw new ApiException("Freelancer information not found",404);
        }

        List<ServiceType> serviceTypeList = serviceTypeRepository.findAllById(freelancer.getServiceTypeList());

        oldFreelancer.setCapacity(freelancer.getCapacity());
        oldFreelancer.setServiceTypeList(serviceTypeList);
        freelancerRepostioty.save(oldFreelancer);

        return true;
    }

    public Freelancer getFreelancer(Integer id){
        Freelancer f= freelancerRepostioty.findByIdEquals(id);
        if(f==null){
            throw new ApiException("Freelancer information not found. complete it.",404);
        }
        return f;

    }
}
