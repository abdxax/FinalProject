package com.example.finalproject.controller.user;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/user/storage")
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;
    @PostMapping("/uploadFile")
    public ResponseEntity<ApiResponse> uploadFile(@RequestParam Integer id,@RequestParam String types ,@AuthenticationPrincipal MyUser user, @RequestParam MultipartFile multipartFile){
     String file= storageService.storeFile(storageService.convertMultiPartFileToFile(multipartFile),id,types,user);
     return ResponseEntity.status(200).body(new ApiResponse(file));
    }
}
