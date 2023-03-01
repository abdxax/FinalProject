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
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AuthRepository authRepository;

    public void addProject(Integer userId,Project project) {
        MyUser user = authRepository.findByIdEquals(userId);
        if (user == null) {
            throw new ApiException("User not found", 404);
        }

        MyUser freelancer = authRepository.findByIdEquals(project.getFreelancerId());
        if (freelancer == null || freelancer.getProfile() == null || freelancer.getProfile().getFreelancer() == null) {
            throw new ApiException("Freelancer not found!", 404);
        }
        if (freelancer.getProfile().getFreelancer().getCapacity() == 0) {
            throw new ApiException("Freelancer does not receive projects currently!", 400);
        }
        project.setCustomerId(userId);
        project.setCustomerApprove(false);
        project.setFreelancerApprove(false);
        project.setStatus(0);
        projectRepository.save(project);
    }

    public List<List<Project>> getProjects(Integer userId) {
        MyUser user = authRepository.findByIdEquals(userId);
        if(user==null){
            throw new ApiException("User not found",404);
        }

        List<List<Project>> projects = new ArrayList<>();
        projects.add(projectRepository.findAllByFreelancerId(userId));
        projects.add(projectRepository.findAllByCustomerId(userId));
        return projects;
    }

    public void acceptProject(Integer userId, Integer projectId) {
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(!Objects.equals(project.getFreelancerId(), userId)){
            throw new ApiException("Project not found",404);
        }
        if(project.getStatus()==2){
            throw new ApiException("Project already canceled",400);
        }
        project.setStatus(1);
        projectRepository.save(project);
    }

    public void rejectProject(Integer id, Integer projectId) {
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(!Objects.equals(project.getFreelancerId(), id)){
            throw new ApiException("Project not found",404);
        }
        if(project.getStatus()==2){
            throw new ApiException("Project already canceled",400);
        }

        project.setStatus(-1);
        projectRepository.save(project);
    }

    public void approveCompletion(Integer userId, Integer projectId){
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(!Objects.equals(project.getFreelancerId(), userId) && !Objects.equals(project.getCustomerId(), userId)){
            throw new ApiException("Project not found",404);
        }
        if(project.getStatus()<=0){
            throw new ApiException("Project not accepted by freelancer",400);
        }
        if(project.getStatus()==2){
            throw new ApiException("Project already canceled",400);
        }
        if(Objects.equals(project.getFreelancerId(), userId)){
            project.setFreelancerApprove(true);
        } else if (Objects.equals(project.getCustomerId(), userId)) {
            project.setCustomerApprove(true);
        }
        if(project.getCustomerApprove()&&project.getFreelancerApprove()){
            project.setStatus(4);
        }

        projectRepository.save(project);

    }

    public void updateProject(Integer id, Project project, Integer userId) {
        Project oldProject = projectRepository.findByIdEquals(id);
        if(oldProject==null){
            throw new ApiException("Project not found",404);
        }
        if(!Objects.equals(oldProject.getCustomerId(), userId)){
            throw new ApiException("Project not found",404);
        }
        oldProject.setBudget(project.getBudget());
        oldProject.setTitle(project.getTitle());
        oldProject.setDescription(project.getDescription());
        oldProject.setStart(project.getStart());
        oldProject.setEnd(project.getEnd());
        projectRepository.save(oldProject);
    }

    public void withdrawApproveCompletion(Integer userId, Integer projectId) {
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(!Objects.equals(project.getFreelancerId(), userId) && !Objects.equals(project.getCustomerId(), userId)){
            throw new ApiException("Project not found",404);
        }
        if(project.getStatus()<=0){
            throw new ApiException("Project not accepted by freelancer",400);
        }
        if(Objects.equals(project.getFreelancerId(), userId)){
            project.setFreelancerApprove(false);
        } else if (Objects.equals(project.getCustomerId(), userId)) {
            project.setCustomerApprove(false);
        }
        if(project.getStatus()!=2) {
            project.setStatus(3);
        }

        projectRepository.save(project);
    }

    public void cancelProject(Integer userId, Integer projectId) {
        Project project = projectRepository.findByIdEquals(projectId);
        if(project==null){
            throw new ApiException("Project not found",404);
        }
        if(!Objects.equals(project.getCustomerId(), userId)){
            throw new ApiException("Project not found",404);
        }
        if(project.getStatus()==4){
            throw new ApiException("Project already completed",400);
        }
        project.setStatus(2);
        project.setBudget(0.0);
        projectRepository.save(project);

    }
}
