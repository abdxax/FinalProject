package com.example.finalproject.service;

import com.example.finalproject.config.FileUploadProperties;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;
    private final AuthRepository authRepository;
    private final ServiceDetailsRepository serviceDetailsRepository;
    private final FileUploadProperties fileUploadProperties;

//    private final StorageService storageService;


    public List<Work> userWork(MyUser user){
        return workRepository.findAllByUser(user);
    }

    public Work getWork(Integer id){
        Work work= workRepository.findWorkById(id);
        if(work==null){
            throw new ApiException("Work not found",404);
        }
        return work;
    }



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

    public void updateWork(Integer id,MyUser user, WorkDTO workDTO) {
        MyUser authUser = authRepository.findByIdEquals(user.getId());
        Work work = getWork(id);
        if(work.getUser().getId() != authUser.getId()){
            throw new ApiException("You don't own this resource",403);
        }

        ServiceDetails serviceDetails = serviceDetailsRepository.findByIdEquals(workDTO.getServiceDetailId());
        if(serviceDetails==null||serviceDetails.getUser().getId()!= authUser.getId()){
            throw new ApiException("Service detail not found.",404);
        }

        work.setDescription(workDTO.getDescription());
        work.setServiceDetails(serviceDetails);
        work.setTitle(workDTO.getTitle());
        workRepository.save(work);
    }

    public void deleteWork(Integer id,MyUser user) {
        MyUser authUser = authRepository.findByIdEquals(user.getId());
        Work work = workRepository.findWorkById(id);
        if(work==null){
            throw new ApiException("Work not found",404);
        }
        if (work.getUser().getId() != authUser.getId()) {
            throw new ApiException("You don't own this resource", 403);
        }
        ;
        if(Files.exists(Paths.get(fileUploadProperties.getLocation()).normalize().resolve("user-"+user.getId()).resolve("works").resolve("work-"+work.getId()))) {
            try {
                try (var dirStream = Files.walk(Paths.get(fileUploadProperties.getLocation()).normalize().resolve("user-" + user.getId()).resolve("works").resolve("work-" + work.getId()))) {
                    dirStream
                            .map(Path::toFile)
                            .sorted(Comparator.reverseOrder())
                            .forEach(File::delete);
                }
                ;
            } catch (IOException e) {
                throw new ApiException("Something went wrong", 500);
            }
        }
        workRepository.delete(work);

    }


    }
