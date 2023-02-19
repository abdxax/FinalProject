package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "serviceType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serviceType;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceType")
    private List<ServiceDetailes> serviceDetailes;
    @ManyToMany
    private List<Freelancer> freelancer;
}
