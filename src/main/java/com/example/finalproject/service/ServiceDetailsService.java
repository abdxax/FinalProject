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
    private final ServiceTypeService serviceTypeService;

    public List<ServiceDetails> serviceDetails(MyUser user){
      return serviceDetailsRepository.findByUser(user);
    }

    public ServiceDetails getServiceDetail(Integer id){
        ServiceDetails serviceDetails = serviceDetailsRepository.findByIdEquals(id);
        if(serviceDetails==null){
            throw new ApiException("Service detail not found",404);
        }
        return serviceDetails;


    }

    public void addServiceDetails(MyUser user, ServiceDetailsDTO detailsDTO){
        ServiceType serviceType = serviceTypeService.getServiceType(detailsDTO.getServiceTypeId());
        MyUser authUser = authRepository.findByIdEquals(user.getId());
        checkServiceTypeAttached(authUser,serviceType);

        ServiceDetails serviceDetails =new ServiceDetails(null,detailsDTO.getDescription(),authUser,serviceType,null);
        serviceDetailsRepository.save(serviceDetails);

    }

    public void update(Integer id, MyUser user, ServiceDetailsDTO serviceDetailsDTO){
        ServiceDetails serviceDetail= getServiceDetail(id);
        ServiceType serviceType = serviceTypeService.getServiceType(serviceDetailsDTO.getServiceTypeId());
        MyUser authUser = authRepository.findByIdEquals(user.getId());

        checkServiceTypeAttached(authUser,serviceType);

        serviceDetail.setServiceType(serviceType);
        serviceDetail.setDescription(serviceDetailsDTO.getDescription());
        serviceDetailsRepository.save(serviceDetail);



    }

    public void delete(Integer id, MyUser user){
        ServiceDetails serviceDetail = getServiceDetail(id);
        if(serviceDetail.getUser().getId() != user.getId()){
            throw new ApiException("You don't own this resource",403);
        }
        serviceDetailsRepository.delete(serviceDetail);
    }

    public void checkServiceTypeAttached(MyUser authUser, ServiceType serviceType){
        if(authUser.getProfile()==null){
            throw new ApiException("Profile not found. complete your profile first.",404);
        }
        if(authUser.getProfile().getFreelancer()==null){
            throw new ApiException("Freelancer information not found. complete your freelancer information first.",404);
        }
        if(!authUser.getProfile().getFreelancer().getServiceTypeList().contains(serviceType)){
            throw new ApiException("You did not attach the service details provided!", 400);
        }

    }



}
