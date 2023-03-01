package com.example.finalproject.repestory;

import com.example.finalproject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    Project findByIdEquals(Integer id);

    List<Project> findAllByFreelancerId(Integer freelancerId);
    List<Project> findAllByCustomerId(Integer customerId);



}
