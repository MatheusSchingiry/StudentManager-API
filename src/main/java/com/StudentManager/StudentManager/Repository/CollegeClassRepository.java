package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CollegeClassRepository extends JpaRepository<CollegeClass, UUID> {
    Page<CollegeClass> findAllByStatus(Status status, Pageable pageable);
}
