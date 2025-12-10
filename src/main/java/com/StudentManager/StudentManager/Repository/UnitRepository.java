package com.StudentManager.StudentManager.Repository;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UnitRepository extends JpaRepository<Unit, UUID> {
    Page<Unit> findAllByStatus(Status status, Pageable pageable);

    @Query("SELECT e FROM Unit e JOIN FETCH e.courses WHERE e.id = :unitId")
    List<Unit> findCoursesIdByUnitId(UUID unitId);
}
