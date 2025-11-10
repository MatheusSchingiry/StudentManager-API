package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.CollegeClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollegeClassRepository extends JpaRepository<CollegeClass, UUID> {
}
