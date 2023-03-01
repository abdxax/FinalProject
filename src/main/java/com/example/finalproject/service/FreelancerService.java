package com.example.finalproject.service;

import com.example.finalproject.dto.FreelancerDTO;
import com.example.finalproject.dto.FreelancerWithService;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.*;
import com.example.finalproject.repestory.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FreelancerService {
    private final FreelancerRepostioty freelancerRepostioty;
    private final ProfileRepository profileRepository;
    private final ServiceTypeRepository serviceTypeRepository;
    private final ServiceDetailsRepository detailsRepository;
    private final AuthRepository authRepository;
    public void addFreelancer(MyUser user, FreelancerDTO freelancerDTO){
        Profile profile=profileRepository.findByIdEquals(user.getId());
        if(profile==null){
            throw new ApiException("Profile not found. Complete profile first.",404);
        }
        Freelancer olfFreelancer = freelancerRepostioty.findByIdEquals(user.getId());
        if(olfFreelancer!=null){
            throw new ApiException("You already have freelancer profile!",400);
        }
        MyUser myUser = authRepository.findByIdEquals(user.getId());
        if(myUser.getRole().equals("USER")){
            myUser.setRole("FREELANCER");
            authRepository.save(myUser);
        }
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAllById(freelancerDTO.getServiceTypeList());

        Freelancer freelancer=new Freelancer(null,freelancerDTO.getCapacity(),freelancerDTO.getMessage(),serviceTypeList,profile);
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

        for (ServiceType s: oldFreelancer.getServiceTypeList()){
            s.getFreelancer().remove(oldFreelancer);
            serviceTypeRepository.save(s);
        }
        List<ServiceType> serviceTypeList = serviceTypeRepository.findAllById(freelancer.getServiceTypeList());

        oldFreelancer.setCapacity(freelancer.getCapacity());
        oldFreelancer.setMessage(freelancer.getMessage());
        for (ServiceType s: serviceTypeList){
            s.getFreelancer().add(oldFreelancer);
            serviceTypeRepository.save(s);
        }
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

    public void addFreelancerWithServoce(FreelancerWithService freelancer,MyUser user){
        ServiceType serviceType=serviceTypeRepository.findByIdEquals(freelancer.getServiceTypeId());
        Profile profile=profileRepository.findByIdEquals(user.getId());
        Freelancer freelanc=freelancerRepostioty.findByProfile(profile);
        if(serviceType==null){
          throw new ApiException("Error id ",400);
        }
        List<ServiceType> serviceTypeList;
       // int size=user.getProfile().getFreelancer().getServiceTypeList().size();
        if(user.getProfile().getFreelancer()==null){
            serviceTypeList=new ArrayList<>();
            serviceTypeList.add(serviceType);
        }
        else{
            serviceTypeList =profile.getFreelancer().getServiceTypeList();
            serviceTypeList.add(serviceType);
        }

        if(freelanc==null){
             freelanc=new Freelancer(null,freelancer.getCapacity(), freelancer.getMessage(),serviceTypeList,profile);
        }
        else{
            freelanc.setServiceTypeList(serviceTypeList);
        }

        freelancerRepostioty.save(freelanc);

        serviceType.getFreelancer().add(freelanc);
        serviceTypeRepository.save(serviceType);

        ServiceDetails serviceDetails=new ServiceDetails(null,freelancer.getDescription(),user,serviceType,null);
        detailsRepository.save(serviceDetails);

        MyUser myUser = authRepository.findByIdEquals(user.getId());
        if(myUser.getRole().equals("USER")){
            myUser.setRole("FREELANCER");
            authRepository.save(myUser);
        }


    }

    public List<Freelancer> getByServiceType(Integer serviceTypeId){
        ServiceType serviceType = serviceTypeRepository.findByIdEquals(serviceTypeId);
        if(serviceType==null){
            throw new ApiException("Service type not found",404);
        }
        return freelancerRepostioty.findAllByServiceTypeListContains(serviceType);

    }
}
