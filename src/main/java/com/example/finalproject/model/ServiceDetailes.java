package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "serviceDetailes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDetailes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private String description;
    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    @JsonIgnore
    private MyUser user;
    @ManyToOne
    @JoinColumn(name = "serviceTypeId",referencedColumnName = "id")
    @JsonIgnore
    private ServiceType serviceType;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceDetalies")
    private List<Storage> storages;



}
