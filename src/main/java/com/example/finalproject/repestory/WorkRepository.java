package com.example.finalproject.repestory;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work,Integer> {
    List<Work> findAllByUser(MyUser user);
}
