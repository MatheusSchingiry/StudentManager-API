package com.StudentManager.StudentManager.Model;

import com.StudentManager.StudentManager.Model.Base.BaseEntity;
import com.StudentManager.StudentManager.Model.Enum.Period;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_courses")
public class Course extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000, nullable = false)
    private String description;

    @Column(nullable = false)
    private Integer workload;

    @ElementCollection(targetClass = Period.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "tb_course_periods", joinColumns = @JoinColumn(name = "course_id"))
    @Column(name = "period")
    private Set<Period> periods;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_course_unit",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private List<Unit> units;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_course_subject",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "subject_id"))
    private List<Subject> subjects;

    @JsonIgnore
    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CollegeClass> collegeClasses;
}
