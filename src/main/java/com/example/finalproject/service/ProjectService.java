package com.example.finalproject.service;

import com.example.finalproject.model.Project;
import com.example.finalproject.repestory.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;

    public void addProject(Project project){
        projectRepository.save(project);
    }

    public Boolean updateApprovFreelancer(Integer id,Boolean approve,Integer freelancerId){
        Project projec=projectRepository.findByIdEquals(id);
        if(projec==null){
            return false;
        }
        if(projec.getFreelancerId()!=freelancerId){
            return  false;
        }
        projec.setFreelancerApprove(approve);
        projectRepository.save(projec);
        return true;
    }

    public Boolean updateApprovCustomer(Integer id,Boolean approve,Integer coustomerId){
        Project projec=projectRepository.findByIdEquals(id);
        if(projec==null){
            return false;
        }
        if(projec.getCustomerId()!=coustomerId){
            return  false;
        }
        projec.setCustomerApprove(approve);
        projectRepository.save(projec);
        return true;
    }
}
