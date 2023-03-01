package com.example.finalproject.controller.user;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.ApiResponseWithUser;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/user/storage")
//@RequiredArgsConstructor
public class StorageController {

    @Autowired
    private StorageService storageService;
    @PostMapping("work/upload/{workId}")
    public ResponseEntity<ApiResponseWithUser> uploadFileToWork(@AuthenticationPrincipal MyUser user, @RequestParam(required = false, value = "file") MultipartFile multipartFile, @PathVariable Integer workId){
        storageService.saveWorkFile(multipartFile,workId,user);
        return ResponseEntity.status(200).body(new ApiResponseWithUser("Uploaded successfully",user));
    }

    @PostMapping("project/upload/{projectId}")
    public ResponseEntity<ApiResponseWithUser> uploadFileToProject(@AuthenticationPrincipal MyUser user, @RequestParam(required = false, value = "file") MultipartFile multipartFile, @PathVariable Integer projectId){
        storageService.saveProjectFile(multipartFile,projectId,user);
        return ResponseEntity.status(200).body(new ApiResponseWithUser("Uploaded successfully",user));
    }

    @GetMapping("")
    public ResponseEntity getFile(@RequestParam("path") String path, @AuthenticationPrincipal MyUser user){
        Resource resource = storageService.loadFile(path);
        return ResponseEntity.status(200).body(resource);

    }

    @GetMapping("{id}")
    public ResponseEntity getFileById(@PathVariable Integer id, @AuthenticationPrincipal MyUser user){
        Resource resource = storageService.loadFileById(id,user.getId());
        return ResponseEntity.status(200).body(resource);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteStorage(@AuthenticationPrincipal MyUser user, @PathVariable Integer id){
        storageService.deleteStorage(id,user.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted successfully"));
    }


}
