package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Page<Course> findAllByStatus(Status status, Pageable pageable);

    @Query("SELECT e FROM Course e JOIN FETCH e.units WHERE e.id = :courseId")
    List<Course> findUnitsIdByCourseId(UUID courseId);
}
