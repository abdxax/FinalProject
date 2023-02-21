package com.example.finalproject.repestory;

import com.example.finalproject.model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {
    MyUser findByEmail(String email);
    MyUser findByIdEquals(Integer id);
}
