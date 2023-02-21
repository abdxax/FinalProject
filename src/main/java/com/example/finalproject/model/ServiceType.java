package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "service_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String serviceType;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceType")
    @JsonIgnore
    private List<ServiceDetails> serviceDetails;
    @ManyToMany
    @JsonIgnore
    private List<Freelancer> freelancer;

    public ServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
