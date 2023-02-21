package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String phone;


    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;



//    private Integer userId;
    @ManyToOne
    @JoinColumn(name = "cityId",referencedColumnName = "id")
    private City city;
    /*@ManyToMany(cascade = CascadeType.ALL,mappedBy = "profile")
    private List<ServiceType> types;

     */
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "profile")
    @PrimaryKeyJoinColumn
    private Freelancer freelancer;

}
