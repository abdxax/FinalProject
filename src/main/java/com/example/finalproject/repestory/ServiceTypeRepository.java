package com.example.finalproject.repestory;

import com.example.finalproject.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceTypeRepository extends JpaRepository<ServiceType,Integer> {
    ServiceType findByIdEquals(Integer id);
}
