package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "works")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Work {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "work")
    private List<Storage> storageList;
    @ManyToOne
    @JoinColumn(name = "serviceDetailsId",referencedColumnName = "id")
    @JsonIgnore
    private ServiceDetails serviceDetails;
    /*@ManyToOne
    @JoinColumn(name = "freelancerId",referencedColumnName = "id")
    @JsonIgnore
    private Freelancer freelancer;

     */
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private MyUser user;





}
