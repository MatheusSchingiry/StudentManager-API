package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {
    @Query("SELECT DISTINCT u FROM Unit u LEFT JOIN FETCH u.courses")
    List<Unit> findAllWithCourses();

    @Query("SELECT u FROM Unit u LEFT JOIN FETCH u.courses WHERE u.id = :id")
    Optional<Unit> findByIdWithCourses(UUID id);
}
