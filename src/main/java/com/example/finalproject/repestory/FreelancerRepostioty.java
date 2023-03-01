package com.example.finalproject.repestory;

import com.example.finalproject.model.Freelancer;
import com.example.finalproject.model.Profile;
import com.example.finalproject.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreelancerRepostioty extends JpaRepository<Freelancer,Integer> {
    Freelancer findByIdEquals(Integer id);
    Freelancer findByProfile(Profile profile);
    List<Freelancer> findAllByServiceTypeListContains(ServiceType serviceType);
}
