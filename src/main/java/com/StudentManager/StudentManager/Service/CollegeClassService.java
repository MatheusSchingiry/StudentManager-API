package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CollegeClassService {

    private final CollegeClassRepository collegeClassRepository;

    public CollegeClassService(CollegeClassRepository collegeClassRepository) { this.collegeClassRepository = collegeClassRepository;}

    public List<CollegeClass> getAllCollegeClasses() { return collegeClassRepository.findAllByStatus(Status.ACTIVE);}

    public CollegeClass getCollegeClassById(UUID id) { return collegeClassRepository.findById(id).orElseThrow(() -> new RuntimeException("CollegeClass not found"));}

    @Transactional
    public CollegeClass createCollegeClass(CollegeClass collegeClass) {
        collegeClass.setStatus(Status.ACTIVE);
        return collegeClassRepository.save(collegeClass);
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
