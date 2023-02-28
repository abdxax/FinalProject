package com.example.finalproject.controller.admin;

import com.example.finalproject.ApiResponse;
import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.CityService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin/city")
@RequiredArgsConstructor
public class CityController {
    private final CityService cityService;
    //@RolesAllowed({ "ADMIN" })
    @GetMapping("/getAll")
    @RolesAllowed("ADMIN")
    public ResponseEntity<List<City>> getAll( ){
        return ResponseEntity.status(200).body(cityService.getCites());
    }

    @PostMapping("/addCity")
    public ResponseEntity<ApiResponse> addCity(@AuthenticationPrincipal MyUser user, @RequestBody @Valid City city){
        cityService.addCity(user,city);
       return ResponseEntity.status(200).body(new ApiResponse("Added Done",user));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@AuthenticationPrincipal MyUser user,@RequestBody @Valid City city){
        Boolean res=cityService.update(user,id,city);
        if(!res){
            return ResponseEntity.status(200).body("has error in something");
        }
        return ResponseEntity.status(200).body("The update done");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id,@AuthenticationPrincipal MyUser user){
        Boolean res=cityService.delete(user,id);
        if(!res){
            return ResponseEntity.status(200).body("has error in something");
        }
        return ResponseEntity.status(200).body("Done Delete");
    }

    @GetMapping("/getCity/{id}")
    public ResponseEntity getCity(@PathVariable Integer id){
        City city=cityService.getCity(id);
        if(city==null){
            return ResponseEntity.status(400).body("Error City CODE");
        }
        return ResponseEntity.status(200).body(city);
    }
}
