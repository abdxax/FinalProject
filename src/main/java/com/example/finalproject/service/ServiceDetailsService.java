package com.example.finalproject.service;

import com.example.finalproject.dto.ServiceDetailsDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetails;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.repestory.AuthRepository;
import com.example.finalproject.repestory.ServiceDetailsRepository;
import com.example.finalproject.repestory.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceDetailsService {
    private final ServiceDetailsRepository serviceDetailsRepository;
    private final AuthRepository authRepository;
    private final ServiceTypeRepository serviceTypeRepository;

    public List<ServiceDetails> serviceDetails(MyUser user){
      return serviceDetailsRepository.findByUser(user);
    }

    public void addServiceDetails(MyUser user, ServiceDetailsDTO detailsDTO){
        ServiceType serviceType=serviceTypeRepository.findByIdEquals(detailsDTO.getServiceTypeId());
        MyUser authUser = authRepository.findByIdEquals(user.getId());
        if(authUser.getProfile()==null){
            throw new ApiException("Profile not found. complete your profile first.",404);
        }
        if(authUser.getProfile().getFreelancer()==null){
            throw new ApiException("Freelancer information not found. complete your freelancer information first.",404);
        }
        if(!authUser.getProfile().getFreelancer().getServiceTypeList().contains(serviceType)){
            throw new ApiException("You did not attach the service details provided!", 400);
        }
        ServiceDetails serviceDetails =new ServiceDetails(null,detailsDTO.getDescription(),authUser,serviceType,null);
        serviceDetailsRepository.save(serviceDetails);

    }

    public Boolean update(Integer id, MyUser user, ServiceDetailsDTO serviceDetailsDTO){
//        ServiceDetails s= serviceDetailsRepository.findByIdEquals(id);
//
//        MyUser u= authRepository.findByIdEquals(serviceDetailsDTO.getUserId());
//        ServiceType serviceType=serviceTypeRepository.findByIdEquals(serviceDetailsDTO.getServiceTypeId());
//
//        if(s==null||u==null||serviceType==null||s.getUser().getId()!=user.getId()){
//            return false;
//        }
//        s.setTitle(serviceDetailsDTO.getTitle());
//        s.setDescription(serviceDetailsDTO.getDescription());
//        serviceDetailsRepository.save(s);
//
        return  true;
    }

    public Boolean delete(Integer id, MyUser user){
        ServiceDetails s= serviceDetailsRepository.findByIdEquals(id);



        if(s==null||s.getUser().getId()!=user.getId()){
            return false;
        }

        serviceDetailsRepository.delete(s);

        return  true;
    }




}
