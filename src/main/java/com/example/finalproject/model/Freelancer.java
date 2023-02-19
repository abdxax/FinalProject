package com.example.finalproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "freelancer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
    private Integer capcity;

    @ManyToMany
    private List<ServiceType> serviceTypeList;

   // private List<Work> works;
   // private List<ServiceDetailes> serviceDetailes;
}
