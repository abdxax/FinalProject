package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "city")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "City name can not be null")
    @Size(min = 2, max = 100, message = "City name must be between 2 and 100 characters")
    private String cityName;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "city")
    @JsonIgnore
    private List<Profile> profiles;

    public City(String cityName) {
        this.cityName = cityName;
    }
}
