package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, UUID> {
    List<Teacher> findAllByStatus(Status status);

    @Query("SELECT e FROM Teacher e JOIN FETCH e.units JOIN FETCH e.subjects WHERE e.id = :teacherId")
    List<Teacher> findUnitsAndSubjectsIdByTeacherId(UUID teacherId);
}
