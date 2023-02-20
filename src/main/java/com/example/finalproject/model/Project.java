package com.example.finalproject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer freelancerId;
    private  Integer customerId;
    private String title;
    private String description;
    private Double budget;
    private Date start;
    private Date end;
    private Boolean customerApprove;
    private Boolean freelancerApprove;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private List<Storage> storages;

}
