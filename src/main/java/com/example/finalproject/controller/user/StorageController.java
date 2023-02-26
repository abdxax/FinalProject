package com.example.finalproject.controller.user;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
    @PostMapping("/upload/{workId}")
    public ResponseEntity<ApiResponse> uploadFile(@AuthenticationPrincipal MyUser user, @RequestParam("file") MultipartFile multipartFile, @PathVariable Integer workId){
        storageService.saveWorkFile(multipartFile,workId,user);
        return ResponseEntity.status(200).body(new ApiResponse("Uploaded successfully"));
    }

    @GetMapping("")
    public ResponseEntity getFile(@RequestParam("path") String path, @AuthenticationPrincipal MyUser user){
        Resource resource = storageService.loadFile(path);
        return ResponseEntity.status(200).body(resource);
    }
}
