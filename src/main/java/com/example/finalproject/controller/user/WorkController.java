package com.example.finalproject.controller.user;

import com.example.finalproject.dto.WorkDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Work;
import com.example.finalproject.service.WorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/user/work")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;

    @PostMapping()
    public ResponseEntity addWork(@AuthenticationPrincipal MyUser user, @RequestBody @Valid WorkDTO workDTO){
        workService.addWork(user,workDTO);
        return ResponseEntity.status(200).body("The work is added");
    }
    @GetMapping()
    public ResponseEntity<List<Work>> workList(@AuthenticationPrincipal MyUser user){
        List<Work> works=workService.userWork(user);
        return ResponseEntity.status(200).body(works);
    }
}
