package com.example.finalproject.service;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.repestory.ServiceTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceTypeService {
    private final ServiceTypeRepository serviceTypeRepository;
    public List<ServiceType> getAll(){
        return serviceTypeRepository.findAll();
    }
    public void addService(MyUser user,ServiceType serviceType){
        if(!checkRole(user)){

        }
        serviceTypeRepository.save(serviceType);

    }

    public Boolean update(MyUser user,Integer id,ServiceType serviceType){
        ServiceType s=serviceTypeRepository.findByIdEquals(id);
        if(!checkRole(user)||s==null){
            return false;
        }
        serviceType.setId(s.getId());
        //serviceType.setProfile(s.getProfile());
        serviceType.setServiceDetails(s.getServiceDetails());
        serviceTypeRepository.save(serviceType);
        return true;
    }

    public Boolean delete(MyUser user,Integer id){
        ServiceType s=serviceTypeRepository.findByIdEquals(id);
        if(!checkRole(user)||s==null){
            return false;
        }

        serviceTypeRepository.delete(s);
        return true;
    }

    public Boolean checkRole(MyUser user){
        if("admin".equalsIgnoreCase(user.getRole())){
            return true;
        }
        return false;
    }
}
