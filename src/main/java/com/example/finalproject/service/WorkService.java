package com.example.finalproject.service;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Work;
import com.example.finalproject.repestory.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkService {
    private final WorkRepository workRepository;

    public void addWork(MyUser user, Work work){
        work.setUser(user);
        workRepository.save(work);
    }

    public List<Work> userWork(MyUser user){
        return workRepository.findAllByUser(user);

    }
}
