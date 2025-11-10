package com.StudentManager.StudentManager.Model;

import com.StudentManager.StudentManager.Model.Base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_registrations")
public class Registration extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Integer semester;

    @Column(nullable = false, updatable = false)
    private LocalDate registrationDate;

    @ManyToOne(optional = false)
    private Student student;

    @ManyToOne
    private CollegeClass collegeClass;
}