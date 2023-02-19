package com.example.finalproject.repestory;

import com.example.finalproject.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepsotery extends JpaRepository<City,Integer> {
    City findByIdEquals(Integer id);
}
