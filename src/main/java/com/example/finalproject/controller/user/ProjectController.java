package com.example.finalproject.controller.user;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Project;
import com.example.finalproject.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/project")
@RequiredArgsConstructor

public class ProjectController {
    private final ProjectService projectService;
    @GetMapping()
    public ResponseEntity<List<List<Project>>> getProjects(@AuthenticationPrincipal MyUser user){
        List<List<Project>> myProjects=projectService.getProjects(user.getId());
        return ResponseEntity.status(200).body(myProjects);
    }
    @PostMapping()
    public ResponseEntity addProject(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Project project){
        projectService.addProject(user.getId(),project);
        return ResponseEntity.status(200).body("Added");
    }

    @PutMapping("accept/{projectId}")
    public ResponseEntity acceptProject(@AuthenticationPrincipal MyUser user,@PathVariable Integer projectId){
        projectService.acceptProject(user.getId(),projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Project was accepted");
    }

    @PutMapping("reject/{projectId}")
    public ResponseEntity rejectProject(@AuthenticationPrincipal MyUser user,@PathVariable Integer projectId){
        projectService.rejectProject(user.getId(),projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Project was rejected");
    }

    @PutMapping("approve/{projectId}")
    public ResponseEntity approveProject(@AuthenticationPrincipal MyUser user,@PathVariable Integer projectId){
        projectService.approveCompletion(user.getId(),projectId);
        return ResponseEntity.status(HttpStatus.OK).body("Project was approved");
    }
//  @PutMapping("/update/{id}")
//    public ResponseEntity update(@AuthenticationPrincipal MyUser user,@RequestBody @Valid FreelancerDTO freelancerDTO){
//      Boolean res=freelancerService.update(user,freelancerDTO);
//      if(!res){
//          ResponseEntity.status(400).body("ERROR");
//      }
//      return ResponseEntity.status(200).body("Update done");
//    }
//@PostMapping("/addFreelancerWithService")
//    public ResponseEntity<ApiResponseWithUser> addResp(@AuthenticationPrincipal MyUser user, @RequestBody @Valid FreelancerWithService freelancer){
//         freelancerService.addFreelancerWithServoce(freelancer,user);
//         return ResponseEntity.status(200).body(new ApiResponseWithUser("Done",user));
//    }


}
