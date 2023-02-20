package com.example.finalproject.repestory;

import com.example.finalproject.model.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepostioty extends JpaRepository<Freelancer,Integer> {
    
}
