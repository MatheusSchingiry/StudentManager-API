package com.StudentManager.StudentManager.Model;

import com.StudentManager.StudentManager.Model.Base.BaseEntity;
import com.StudentManager.StudentManager.Model.Enum.Period;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_college_classes")
public class CollegeClass extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private Period period;

    @ManyToOne(optional = false)
    private Unit unit;

    @ManyToOne(optional = false)
    private Course course;

    @OneToMany(mappedBy = "collegeClass")
    private List<Registration> registrations;
}
