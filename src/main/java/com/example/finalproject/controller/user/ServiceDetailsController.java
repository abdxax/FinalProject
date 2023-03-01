package com.example.finalproject.controller.user;

import com.example.finalproject.ApiResponseWithUser;
import com.example.finalproject.dto.ServiceDetailsDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetails;
import com.example.finalproject.model.ServiceType;
import com.example.finalproject.service.ServiceDetailsService;
import com.example.finalproject.service.ServiceTypeService;
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
    private  final ServiceTypeService serviceTypeService;

    @PostMapping()
    public ResponseEntity addServiceDetails(@AuthenticationPrincipal MyUser user, @RequestBody @Valid ServiceDetailsDTO serviceDetailsDTO){
        serviceDetailsService.addServiceDetails(user,serviceDetailsDTO);
        return ResponseEntity.status(200).body(new ApiResponseWithUser("Service details added",user));
    }
    @GetMapping()
    public ResponseEntity<List<ServiceDetails>> getServiceDetails(@AuthenticationPrincipal MyUser user){
        List<ServiceDetails> serviceDetails=serviceDetailsService.serviceDetails(user);
        return ResponseEntity.status(200).body(serviceDetails);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponseWithUser> updateServiceDetail(@AuthenticationPrincipal MyUser user, @PathVariable Integer id, @RequestBody ServiceDetailsDTO serviceDetailsDTO){
        serviceDetailsService.update(id,user,serviceDetailsDTO);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseWithUser("Updated successfully",user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponseWithUser> deleteServiceDetail(@AuthenticationPrincipal MyUser user, @PathVariable Integer id){
        serviceDetailsService.delete(id,user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponseWithUser("Deleted successfully",user));
    }
    @GetMapping("/getServiceType")
    public ResponseEntity<List<ServiceType>> getType(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(serviceTypeService.getAll());
    }
    @GetMapping("/getServiceByType/{id}")
    public ResponseEntity<List<ServiceDetails>> getServiceD(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(serviceDetailsService.serviceDetailsByType(id));
    }
}
