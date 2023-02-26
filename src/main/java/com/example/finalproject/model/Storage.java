package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "storage")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String fileType;
    private String filePath;
    @ManyToOne
    @JoinColumn(name = "workId",referencedColumnName = "id")
    @JsonIgnore
    private Work work;
    @ManyToOne
    @JoinColumn(name = "projectId",referencedColumnName = "id")
    @JsonIgnore
    private Project project;
}
