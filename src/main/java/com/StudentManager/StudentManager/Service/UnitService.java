package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UnitService {

    private final UnitRepository unitRepository;

    public UnitService(UnitRepository unitRepository) { this.unitRepository = unitRepository;}

    @Transactional
    public List<Unit> getAllUnits() { return unitRepository.findAll(); }

    public Unit getUnitById(UUID id) { return unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));}

    @Transactional
    public Unit createUnit(Unit unit){
        unit.setStatus(Status.ACTIVE);
        return unitRepository.save(unit);
    }

    @Transactional
    public Unit updateUnit(UUID id, Unit unit){
        Unit existingUnit = unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));

        if(unit.getName() != null) { existingUnit.setName(unit.getName());}
        if(unit.getAddress() != null) { existingUnit.setAddress(unit.getAddress());}

        return unitRepository.save(existingUnit);
    }

    @Transactional
    public void deleteUnit(UUID id){
        Unit existingUnit = unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));

        boolean hasActiveCourses = existingUnit.getCourses()
                .stream()
                .anyMatch(course -> course.getStatus() == Status.ACTIVE);

        if (hasActiveCourses) {
            throw new RuntimeException("Cannot delete Unit with active Courses");
        }

        existingUnit.setStatus(Status.INACTIVE);
        unitRepository.save(existingUnit);
    }
}
