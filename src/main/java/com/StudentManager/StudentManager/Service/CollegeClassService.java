package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.CollegeClassResponse;
import com.StudentManager.StudentManager.Mapper.CollegeClassMapper;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Period;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(readOnly = true)
    public List<CollegeClassResponse> getAllCollegeClasses() {
        return collegeClassRepository.findAll()
                .stream()
                .map(collegeClassMapper::toCollegeClassResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CollegeClassResponse getCollegeClassById(UUID id) {
        return collegeClassMapper.toCollegeClassResponse(collegeClassRepository.findById(id).orElseThrow(() -> new RuntimeException("CollegeClass not found")));
    }

    @Transactional(readOnly = true)
    public List<CollegeClassResponse> getCollegeClassByPeriod(Period period) {
        return collegeClassRepository.findByPeriod(period)
                .stream()
                .map(collegeClassMapper::toCollegeClassResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CollegeClassResponse> getCollegeClassByRegistrationId(UUID registrationId) {
        return collegeClassRepository.findByRegistrationsId(registrationId)
                .stream()
                .map(collegeClassMapper::toCollegeClassResponse)
                .collect(Collectors.toList());
    }

    public CollegeClassResponse createCollegeClass(CollegeClassRequest collegeClass) {
        CollegeClass newCollegeClass = collegeClassMapper.toCollegeClass(collegeClass);
        collegeClassRepository.save(newCollegeClass);
        return collegeClassMapper.toCollegeClassResponse(newCollegeClass);
    }

    public CollegeClassResponse editCollegeClass(UUID id, CollegeClassRequest collegeClass) {
        CollegeClass existingCollegeClass = collegeClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CollegeClass not found"));

        if(collegeClass.period() != null){ existingCollegeClass.setStatus(collegeClass.status()); }
        if(collegeClass.status() != null){ existingCollegeClass.setPeriod(collegeClass.period()); }

        return collegeClassMapper.toCollegeClassResponse(collegeClassRepository.save(existingCollegeClass));
    }

    public void deleteCollegeClass(UUID id) {
        CollegeClass existingCollegeClass = collegeClassRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CollegeClass not found"));

        existingCollegeClass.setStatus(Status.INACTIVE);
        collegeClassRepository.save(existingCollegeClass);
    }


}
