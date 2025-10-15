package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    Optional<Registration> findByStudentId(UUID studentId);
    Optional<Registration> findByCollegeClassId(UUID collegeClassId);
}
