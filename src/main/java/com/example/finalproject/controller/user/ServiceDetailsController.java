package com.example.finalproject.controller.user;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.dto.ServiceDetailsDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetails;
import com.example.finalproject.model.Work;
import com.example.finalproject.service.ServiceDetailsService;
import com.example.finalproject.service.WorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/service/details")
@RequiredArgsConstructor
public class ServiceDetailsController {
    private final ServiceDetailsService serviceDetailsService;

    @PostMapping()
    public ResponseEntity addServiceDetails(@AuthenticationPrincipal MyUser user, @RequestBody @Valid ServiceDetailsDTO serviceDetailsDTO){
        serviceDetailsService.addServiceDetails(user,serviceDetailsDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Service details added",user));
    }
    @GetMapping()
    public ResponseEntity<List<ServiceDetails>> getServiceDetails(@AuthenticationPrincipal MyUser user){
        List<ServiceDetails> serviceDetails=serviceDetailsService.serviceDetails(user);
        return ResponseEntity.status(200).body(serviceDetails);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> updateServiceDetail(@AuthenticationPrincipal MyUser user, @PathVariable Integer id, @RequestBody ServiceDetailsDTO serviceDetailsDTO){
        serviceDetailsService.update(id,user,serviceDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Updated successfully",user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> deleteServiceDetail(@AuthenticationPrincipal MyUser user, @PathVariable Integer id){
        serviceDetailsService.delete(id,user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Deleted successfully",user));
    }
}
