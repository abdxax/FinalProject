package com.example.finalproject.repestory;

import com.example.finalproject.model.Freelancer;
import com.example.finalproject.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FreelancerRepostioty extends JpaRepository<Freelancer,Integer> {
    Freelancer findByIdEquals(Integer id);
    Freelancer findByProfile(Profile profile);
}
