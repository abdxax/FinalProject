package com.example.finalproject.controller.admin;

import com.example.finalproject.model.City;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.CityService;
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
    @GetMapping("/getAll")
    public ResponseEntity<List<City>> getAll(@AuthenticationPrincipal MyUser user){
        return ResponseEntity.status(200).body(cityService.getCites());
    }

    @PostMapping("/addCity")
    public ResponseEntity addCity(@AuthenticationPrincipal MyUser user,@RequestBody @Valid City city){
        cityService.addCity(user,city);
       return ResponseEntity.status(200).body("Added Done ");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity update(@PathVariable Integer id,@AuthenticationPrincipal MyUser user,City city){
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
}
