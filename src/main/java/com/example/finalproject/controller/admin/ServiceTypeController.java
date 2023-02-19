package com.example.finalproject.controller.admin;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
@RequiredArgsConstructor
public class ServiceTypeController {
    private final ServiceTypeService serviceTypeService;
    @GetMapping("/getAll")
    public ResponseEntity<List<ServiceType>> getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(serviceTypeService.getAll());
    }
    public ResponseEntity addServiceType(@AuthenticationPrincipal MyUser user,ServiceType serviceType){


        return ResponseEntity.status(200).body("");
    }


}
