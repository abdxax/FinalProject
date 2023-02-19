package com.example.finalproject.controller.admin;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.service.ServiceTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/addService")
    public ResponseEntity addServiceType(@AuthenticationPrincipal MyUser user,ServiceType serviceType){

         serviceTypeService.addService(user,serviceType);
        return ResponseEntity.status(200).body("");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@AuthenticationPrincipal MyUser user,ServiceType serviceType){
        Boolean res=serviceTypeService.update(user,id,serviceType);
        if(!res){
            return ResponseEntity.status(400).body("The error in somePlace");
        }
        return ResponseEntity.status(200).body("Done Update");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        Boolean res=serviceTypeService.delete(user,id);
        if(!res){
            return ResponseEntity.status(400).body("The error in somePlace");
        }
        return ResponseEntity.status(200).body("Done Delete");
    }


}
