package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.UnitRequest;
import com.StudentManager.StudentManager.DTO.Response.UnitBaseResponse;
import com.StudentManager.StudentManager.Mapper.UnitMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    public UnitService(UnitRepository unitRepository, UnitMapper unitMapper) {
        this.unitRepository = unitRepository;
        this.unitMapper = unitMapper;
    }

    @Transactional
    public List<UnitBaseResponse> getAllUnits() {
        return unitRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(unitMapper::toUnitBaseResponse)
                .collect(Collectors.toList());
    }

    public UnitBaseResponse getUnitById(UUID id) {
        return unitMapper.toUnitBaseResponse(unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found")));
    }

    @Transactional
    public UnitBaseResponse createUnit(UnitRequest unit){
        Unit unitEntity = unitMapper.toUnit(unit);
        unitEntity.setStatus(Status.ACTIVE);
        return unitMapper.toUnitBaseResponse(unitRepository.save(unitEntity));
    }

    @Transactional
    public UnitBaseResponse updateUnit(UUID id, UnitRequest unit){
        Unit existingUnit = unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));

        if(unit.name() != null) { existingUnit.setName(unit.name());}
        if(unit.address() != null) { existingUnit.setAddress(unit.address());}

        return unitMapper.toUnitBaseResponse(unitRepository.save(existingUnit));
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
