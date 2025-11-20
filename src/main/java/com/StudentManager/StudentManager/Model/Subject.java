package com.StudentManager.StudentManager.Model;

import com.StudentManager.StudentManager.Model.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_subjects")
public class Subject extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer creditHours;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private Set<Course> courses;

    @JsonIgnore
    @ManyToMany(mappedBy = "subjects")
    private Set<Teacher> teachers;
}
