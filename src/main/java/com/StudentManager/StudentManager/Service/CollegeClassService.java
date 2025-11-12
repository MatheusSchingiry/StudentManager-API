package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.Response.CollegeClassBaseResponse;
import com.StudentManager.StudentManager.Mapper.CollegeClassMapper;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CollegeClassService {

    private final CollegeClassRepository collegeClassRepository;
    private final CollegeClassMapper collegeClassMapper;

    public CollegeClassService(CollegeClassRepository collegeClassRepository, CollegeClassMapper collegeClassMapper) {
        this.collegeClassRepository = collegeClassRepository;
        this.collegeClassMapper = collegeClassMapper;
    }

    public List<CollegeClassBaseResponse> getAllCollegeClasses() {
        return collegeClassRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(collegeClassMapper::toCollegeClassBaseResponse)
                .collect(Collectors.toList());
    }

    public CollegeClassBaseResponse getCollegeClassById(UUID id) {
        return collegeClassMapper.toCollegeClassBaseResponse(collegeClassRepository.findById(id).orElseThrow(() -> new RuntimeException("CollegeClass not found")));
    }

    @Transactional
    public CollegeClassBaseResponse createCollegeClass(CollegeClassRequest collegeClass) {
        CollegeClass collegeClassEntity = collegeClassMapper.toCollegeClass(collegeClass);
        collegeClassEntity.setStatus(Status.ACTIVE);
        return collegeClassMapper.toCollegeClassBaseResponse(collegeClassRepository.save(collegeClassEntity));
    }

    @Transactional
    public void deleteCollegeClass(UUID id) {
        CollegeClass existingCollegeClass = collegeClassRepository.findById(id).orElseThrow(() -> new RuntimeException("CollegeClass not found"));

        boolean hasActiveRegistrations = existingCollegeClass.getRegistrations()
                .stream()
                .anyMatch(registration -> registration.getStatus() == Status.ACTIVE);

        if (hasActiveRegistrations) {
            throw new RuntimeException("Cannot delete College Class with active registrations");
        }

        existingCollegeClass.setStatus(Status.INACTIVE);
        collegeClassRepository.save(existingCollegeClass);
    }
}
