package com.example.finalproject.repestory;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetails;
import com.example.finalproject.model.ServiceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDetailsRepository extends JpaRepository<ServiceDetails,Integer> {
    List<ServiceDetails> findByUser(MyUser user);
    ServiceDetails findByIdEquals(Integer id);

    List<ServiceDetails> findByserviceType(ServiceType serviceType);
}
