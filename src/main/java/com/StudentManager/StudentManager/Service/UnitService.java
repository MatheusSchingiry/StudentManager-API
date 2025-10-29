package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.CourseResponse;
import com.StudentManager.StudentManager.DTO.UnitRequest;
import com.StudentManager.StudentManager.DTO.UnitResponse;
import com.StudentManager.StudentManager.Mapper.UnitMapper;
import com.StudentManager.StudentManager.Model.Course;
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
    public List<UnitResponse> getAllUnits() {
        return unitRepository.findAllWithCourses()
                .stream()
                .map(unitMapper::toUnitResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public UnitResponse getUnitById(UUID id) {
        Unit unit = unitRepository.findByIdWithCourses(id)
                .orElseThrow(() -> new RuntimeException("Unit not found"));

        return unitMapper.toUnitResponse(unit);
    }

    public UnitResponse createUnit(UnitRequest unit){
        Unit newUnit = unitMapper.toUnit(unit);
        unitRepository.save(newUnit);
        return unitMapper.toUnitResponse(newUnit);
    }

    public UnitResponse editUnitById(UUID id, UnitRequest unit){
        Unit existingUnit = unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));

        if(unit.name() != null) { existingUnit.setName(unit.name());}
        if(unit.street() != null) { existingUnit.setStreet(unit.street());}
        if(unit.number() != null) { existingUnit.setNumber(unit.number());}
        if(unit.city() != null) { existingUnit.setCity(unit.city());}
        if(unit.state() != null) { existingUnit.setState(unit.state());}
        if(unit.zipCode() != null) { existingUnit.setZipCode(unit.zipCode());}

        unitRepository.save(existingUnit);
        return unitMapper.toUnitResponse(existingUnit);
    }

    public void deleteUnitById(UUID id){
        Unit existingUnit = unitRepository.findById(id).orElseThrow(() -> new RuntimeException("Unit not found"));
        existingUnit.setStatus(Status.INACTIVE);
        unitRepository.save(existingUnit);
    }
}
