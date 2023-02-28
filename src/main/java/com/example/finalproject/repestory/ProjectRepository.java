package com.example.finalproject.repestory;

import com.example.finalproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findByIdEquals(Integer id);


}
