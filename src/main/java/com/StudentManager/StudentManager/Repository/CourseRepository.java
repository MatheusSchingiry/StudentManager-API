package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    @Query("SELECT DISTINCT c FROM Course c LEFT JOIN FETCH c.units")
    List<Course> findAllWithUnits();

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.units WHERE c.id = :id")
    Optional<Course> findByIdWithUnits(UUID id);
}
