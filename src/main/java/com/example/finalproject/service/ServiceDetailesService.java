package com.example.finalproject.service;

import com.example.finalproject.dto.ServiceDetailsDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetails;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.repestory.AuthRepstory;
import com.example.finalproject.repestory.ServiceDetailsRepository;
import com.example.finalproject.repestory.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceDetailesService {
    private final ServiceDetailsRepository serviceDetailsRepository;
    private final AuthRepstory authRepstory;
    private final ServiceTypeRepository serviceTypeRepository;

    public List<ServiceDetails> serviceDetails(MyUser user){
      return serviceDetailsRepository.findByUser(user);
    }

    public void addServiceDetails(MyUser user, ServiceDetailsDTO detailsDTO){
        MyUser u=authRepstory.findByIdEquals(detailsDTO.getUserId());
        ServiceType serviceType=serviceTypeRepository.findByIdEquals(detailsDTO.getServiceTypeId());
        if(u==null||serviceType==null||user.getId()!=detailsDTO.getUserId()){
            throw new ApiException("Error save something mistake",400);
        }
       // ServiceDetails serviceDetails=new ServiceDetails(null,detailesDTO.getTitle(), detailesDTO.getDescription(),user,serviceType,null);
      //  ServiceDetails serviceDetails=new ServiceDetails(null,detailesDTO.getTitle(),detailesDTO.getDescription(),user,serviceType,null,user.getProfile().getFreelancer());
        ServiceDetails serviceDetails =new ServiceDetails(null,detailsDTO.getTitle(),detailsDTO.getDescription(),user,serviceType,null);
        serviceDetailsRepository.save(serviceDetails);

    }

    public Boolean update(Integer id, MyUser user, ServiceDetailsDTO serviceDetailsDTO){
        ServiceDetails s= serviceDetailsRepository.findByIdEquals(id);

        MyUser u=authRepstory.findByIdEquals(serviceDetailsDTO.getUserId());
        ServiceType serviceType=serviceTypeRepository.findByIdEquals(serviceDetailsDTO.getServiceTypeId());

        if(s==null||u==null||serviceType==null||s.getUser().getId()!=user.getId()){
            return false;
        }
        s.setTitle(serviceDetailsDTO.getTitle());
        s.setDescription(serviceDetailsDTO.getDescription());
        serviceDetailsRepository.save(s);

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
