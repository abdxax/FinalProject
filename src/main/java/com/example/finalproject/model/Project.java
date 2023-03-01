package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
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
    @NotNull(message = "Title can not be null")
    @Size(min = 5, max = 150, message = "Title must be between 5 and 150 characters")
    private String title;
    @NotNull(message = "Description can not be null")
    @Size(min = 15, message = "Description can not be less than 15 characters")
    private String description;
    @PositiveOrZero(message = "Budget must be a positive integer")
    private Double budget;
    @NotNull(message = "Start date can not be null")
    @Future(message = "Start date must be in future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date start;
    @NotNull(message = "End date can not be null")
    @Future(message = "End date must be in future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date end;
    @Column(columnDefinition = "boolean default false")
    private Boolean customerApprove;
    @Column(columnDefinition = "boolean default false")
    private Boolean freelancerApprove;
    @Column(columnDefinition = "int default 0")
    private Integer status; //   freelancer reject: -1, freelancer waiting: 0, freelancer accept: 1, canceled: 2, running: 3, completed: 4
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "project")
    private List<Storage> storages;

}
