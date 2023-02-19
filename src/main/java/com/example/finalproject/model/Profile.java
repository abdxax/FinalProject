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
@Table(name = "profile")
public class Profile {
    @Id
    private Integer id;
    private String name;
    private String phone;


    @OneToOne
    @MapsId
    @JsonIgnore
    private MyUser user;
    @ManyToOne
    @JoinColumn(name = "cityId",referencedColumnName = "id")
    @JsonIgnore
    private City city;
    /*@ManyToMany(cascade = CascadeType.ALL,mappedBy = "profile")
    private List<ServiceType> types;

     */
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Freelancer freelancer;

}
