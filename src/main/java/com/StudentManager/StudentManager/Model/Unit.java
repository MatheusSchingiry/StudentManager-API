package com.StudentManager.StudentManager.Model;

import com.StudentManager.StudentManager.Model.Base.Address;
import com.StudentManager.StudentManager.Model.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_units")
public class Unit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Embedded
    private Address address;

    @ManyToMany(mappedBy = "units")
    private Set<Course> courses;

    @ManyToMany(mappedBy = "units")
    private Set<Teacher> teachers;

    @OneToMany(mappedBy = "unit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollegeClass> collegeClasses;
}
