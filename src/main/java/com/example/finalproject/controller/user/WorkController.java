package com.example.finalproject.controller.user;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Work;
import com.example.finalproject.service.WorkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user/work")
@RequiredArgsConstructor
public class WorkController {
    private final WorkService workService;

    @PostMapping("/addWork")
    public ResponseEntity addWork(@AuthenticationPrincipal MyUser user, @RequestBody @Valid Work work){
        workService.addWork(user,work);
        return ResponseEntity.status(200).body("The work is added");
    }
}
