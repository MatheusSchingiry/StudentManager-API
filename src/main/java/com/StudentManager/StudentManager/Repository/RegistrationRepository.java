package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Registration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, UUID> {
    Page<Registration> findAllByStatus(Status status, Pageable pageable);
}
