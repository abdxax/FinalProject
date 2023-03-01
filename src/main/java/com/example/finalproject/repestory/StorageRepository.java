package com.example.finalproject.repestory;

import com.example.finalproject.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Integer> {

    Storage findStorageById(Integer id);
}
