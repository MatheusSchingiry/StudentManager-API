package com.StudentManager.StudentManager.Model;

import com.StudentManager.StudentManager.Model.Base.Address;
import com.StudentManager.StudentManager.Model.Base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_teachers")
public class Teacher extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String registerNumber;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Embedded
    private Address address;

    @Column(unique = true, nullable = false)
    private String email;

    private String phoneNumber;

    @Column(nullable = false)
    private String specialty;

    @Column(nullable = false, updatable = false)
    private LocalDate hireDate;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_teacher_unit",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private Set<Unit> units;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_teacher_subject",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private Set<Subject> subjects;
}
