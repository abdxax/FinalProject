package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Entity
@Table(name = "freelancers_information")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Freelancer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;
    private Integer capacity;

    private String message;
    @ManyToMany(mappedBy = "freelancer")
    private List<ServiceType> serviceTypeList;

    @OneToOne
    @MapsId
    @JsonIgnore
    private Profile profile;
   /* @OneToMany(cascade = CascadeType.ALL,mappedBy = "freelancer")
    private List<Work> works;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "freelancer")
    private List<ServiceDetails> serviceDetailes;

    */
}
