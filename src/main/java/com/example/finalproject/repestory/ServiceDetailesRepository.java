package com.example.finalproject.repestory;

import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.ServiceDetailes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceDetailesRepository extends JpaRepository<ServiceDetailes,Integer> {
    List<ServiceDetailes> findByUser(MyUser user);
    ServiceDetailes findByIdEquals(Integer id);
}
