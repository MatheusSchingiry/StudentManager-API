package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.Response.CollegeClassBaseResponse;
import com.StudentManager.StudentManager.Exception.ConflictException;
import com.StudentManager.StudentManager.Exception.NotFoundException;
import com.StudentManager.StudentManager.Mapper.CollegeClassMapper;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CollegeClassService {

    private final CollegeClassRepository collegeClassRepository;
    private final CollegeClassMapper collegeClassMapper;
    private final UnitRepository unitRepository;
    private final CourseRepository courserRepository;

    public CollegeClassService(CollegeClassRepository collegeClassRepository, CollegeClassMapper collegeClassMapper, UnitRepository unitRepository, CourseRepository courserRepository) {
        this.collegeClassRepository = collegeClassRepository;
        this.collegeClassMapper = collegeClassMapper;
        this.unitRepository = unitRepository;
        this.courserRepository = courserRepository;
    }

    public List<CollegeClassBaseResponse> getAllCollegeClasses() {
        return collegeClassRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(collegeClassMapper::toCollegeClassBaseResponse)
                .collect(Collectors.toList());
    }

    public CollegeClassBaseResponse getCollegeClassById(UUID id) {
        return collegeClassMapper.toCollegeClassBaseResponse(collegeClassRepository.findById(id).orElseThrow(() -> new NotFoundException("CollegeClass not found")));
    }

    @Transactional
    public CollegeClassBaseResponse createCollegeClass(CollegeClassRequest collegeClass) {
        Unit unit = unitRepository.findById(collegeClass.unitId())
                .orElseThrow(() -> new NotFoundException("Unit not found"));

        Course course = courserRepository.findById(collegeClass.course())
                .orElseThrow(() -> new NotFoundException("Course not found"));

        CollegeClass collegeClassEntity = collegeClassMapper.toCollegeClass(collegeClass, unit, course);
        collegeClassEntity.setStatus(Status.ACTIVE);
        return collegeClassMapper.toCollegeClassBaseResponse(collegeClassRepository.save(collegeClassEntity));
    }

    @Transactional
    public void deleteCollegeClass(UUID id) {
        CollegeClass existingCollegeClass = collegeClassRepository.findById(id).orElseThrow(() -> new NotFoundException("CollegeClass not found"));

        boolean hasActiveRegistrations = existingCollegeClass.getRegistrations()
                .stream()
                .anyMatch(registration -> registration.getStatus() == Status.ACTIVE);

        if (hasActiveRegistrations) {
            throw new ConflictException("Cannot delete College Class with active registrations");
        }

        existingCollegeClass.setStatus(Status.INACTIVE);
        collegeClassRepository.save(existingCollegeClass);
    }
}
