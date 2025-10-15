package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CollegeClassRepository extends JpaRepository<CollegeClass, UUID> {
    List<CollegeClass> findByPeriod(Period period);
    List<CollegeClass> findByRegistrationsId(UUID registrationId);

}
