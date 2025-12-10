package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StudentRepository extends JpaRepository<Student, UUID> {
    Page<Student> findAllByStatus(Status status, Pageable pageable);
    Boolean existsByEmail(String email);
    Boolean existsByRegisterNumber(String registerNumber);
}
