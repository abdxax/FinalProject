package com.example.finalproject.service;

import com.example.finalproject.dto.ServiceDetailesDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetailes;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.repestory.AuthRepstory;
import com.example.finalproject.repestory.ServiceDetailesRepository;
import com.example.finalproject.repestory.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceDetailesService {
    private final ServiceDetailesRepository serviceDetailesRepository;
    private final AuthRepstory authRepstory;
    private final ServiceTypeRepository serviceTypeRepository;

    public List<ServiceDetailes> serviceDetailes(MyUser user){
      return serviceDetailesRepository.findByUser(user);
    }

    public void addServiceDetailes(MyUser user,ServiceDetailesDTO detailesDTO){
        MyUser u=authRepstory.findByIdEquals(detailesDTO.getUserId());
        ServiceType serviceType=serviceTypeRepository.findByIdEquals(detailesDTO.getServiecTypeId());
        if(u==null||serviceType==null||user.getId()!=detailesDTO.getUserId()){
            throw new ApiException("Error save something mistake");
        }
        ServiceDetailes serviceDetailes=new ServiceDetailes(null,detailesDTO.getTitle(), detailesDTO.getDescription(),user,serviceType,null);
        serviceDetailesRepository.save(serviceDetailes);

    }

    public Boolean update(Integer id, MyUser user,ServiceDetailesDTO serviceDetailesDTO){
        ServiceDetailes s=serviceDetailesRepository.findByIdEquals(id);

        MyUser u=authRepstory.findByIdEquals(serviceDetailesDTO.getUserId());
        ServiceType serviceType=serviceTypeRepository.findByIdEquals(serviceDetailesDTO.getServiecTypeId());

        if(s==null||u==null||serviceType==null||s.getUser().getId()!=user.getId()){
            return false;
        }
        s.setTitle(serviceDetailesDTO.getTitle());
        s.setDescription(serviceDetailesDTO.getDescription());
        serviceDetailesRepository.save(s);

        return  true;
    }

    public Boolean delete(Integer id, MyUser user){
        ServiceDetailes s=serviceDetailesRepository.findByIdEquals(id);



        if(s==null||s.getUser().getId()!=user.getId()){
            return false;
        }

        serviceDetailesRepository.delete(s);

        return  true;
    }




}
