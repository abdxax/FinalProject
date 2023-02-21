package com.example.finalproject.service;

import com.example.finalproject.dto.WorkDTO;
import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetails;
import com.example.finalproject.model.Work;
import com.example.finalproject.repestory.AuthRepository;
import com.example.finalproject.repestory.ServiceDetailsRepository;
import com.example.finalproject.repestory.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final AuthRepository authRepository;
    private final ServiceDetailsRepository serviceDetailsRepository;


    public void addWork(MyUser user, WorkDTO workDTO){
        MyUser authUser = authRepository.findByIdEquals(user.getId());
        if(authUser.getProfile()==null){
            throw new ApiException("Profile not found. complete profile first.",404);
        }
        if(authUser.getProfile().getFreelancer()==null){
            throw new ApiException("You did not complete your freelancer information.",400);
        }
        ServiceDetails serviceDetails = serviceDetailsRepository.findByIdEquals(workDTO.getServiceDetailId());
        if(serviceDetails==null||serviceDetails.getUser().getId()!= authUser.getId()){
            throw new ApiException("Service detail not found.",404);
        }
        Work work = new Work(null,workDTO.getTitle(),workDTO.getDescription(),null,serviceDetails,authUser);
        workRepository.save(work);
    }

    public List<Work> userWork(MyUser user){
        return workRepository.findAllByUser(user);
    }
}
