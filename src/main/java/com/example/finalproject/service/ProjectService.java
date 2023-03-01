package com.example.finalproject.service;

import com.example.finalproject.handling.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Project;
import com.example.finalproject.repestory.AuthRepository;
import com.example.finalproject.repestory.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AuthRepository authRepository;

    public void addProject(Integer userId,Project project){
        MyUser user = authRepository.findByIdEquals(userId);
        if(user==null){
            throw new ApiException("User not found",404);
        }

        MyUser freelancer = authRepository.findByIdEquals(project.getFreelancerId());
        if(freelancer==null||freelancer.getProfile()==null||freelancer.getProfile().getFreelancer()==null){
            throw new ApiException("Freelancer not found!",404);
        }
        project.setCustomerId(userId);
        project.setCustomerApprove(false);
        project.setFreelancerApprove(false);
        project.setStatus(0);
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

    public List<List<Project>> getProjects(Integer id) {
        MyUser user = authRepository.findByIdEquals(id);
        if(user==null){
            throw new ApiException("User not found",404);
        }
//        if(user.getProfile()==null){
//            throw new ApiException("Profile not found",404);
//        }
//        if(user.getProfile().getFreelancer()==null){
//            throw new ApiException("You are not a freelancer",400);
//        }
        List<List<Project>> projects = new ArrayList<>();

        projects.add(projectRepository.findAllByFreelancerId(id));
        projects.add(projectRepository.findAllByCustomerId(id));
        return projects;
    }

    public void acceptProject(Integer id, Integer projectId) {
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(project.getFreelancerId()!= id){
            throw new ApiException("Project not found",404);
        }

        project.setStatus(1);
        projectRepository.save(project);
    }

    public void rejectProject(Integer id, Integer projectId) {
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(project.getFreelancerId()!= id){
            throw new ApiException("Project not found",404);
        }

        project.setStatus(-1);
        projectRepository.save(project);
    }

    public void approveCompletion(Integer id, Integer projectId){
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(project.getFreelancerId() != id && project.getCustomerId()!=id){
            throw new ApiException("Project not found",404);
        }
        if(project.getFreelancerId()==id){
            project.setFreelancerApprove(true);
        } else if (project.getCustomerId()==id) {
            project.setCustomerApprove(true);
        }
        if(project.getCustomerApprove()&&project.getFreelancerApprove()){
            project.setStatus(4);
        }

        projectRepository.save(project);

    }
}
