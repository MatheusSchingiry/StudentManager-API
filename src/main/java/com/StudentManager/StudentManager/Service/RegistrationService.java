package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.Response.RegistrationBaseResponse;
import com.StudentManager.StudentManager.Mapper.RegistrationMapper;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Model.Student;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import com.StudentManager.StudentManager.Repository.RegistrationRepository;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CollegeClassRepository collegeClassRepository;
    private final RegistrationMapper registrationMapper;

    public RegistrationService(RegistrationRepository registrationRepository, StudentRepository studentRepository, CollegeClassRepository collegeClassRepository, RegistrationMapper registrationMapper) {
        this.registrationRepository = registrationRepository;
        this.studentRepository = studentRepository;
        this.collegeClassRepository = collegeClassRepository;
        this.registrationMapper = registrationMapper;
    }

    public List<RegistrationBaseResponse> getAllRegistrations(){
        return registrationRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(registrationMapper::toRegistrationBaseResponse)
                .collect(Collectors.toList());
    }

    public RegistrationBaseResponse getRegistrationById(UUID id) {
        return registrationMapper.toRegistrationBaseResponse(registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found")));
    }

    @Transactional
    public RegistrationBaseResponse createRegistration(RegistrationRequest registration) {
        Student student = studentRepository.findById(registration.studentId()).orElseThrow(() -> new RuntimeException("Student not found"));
        CollegeClass collegeClass = collegeClassRepository.findById(registration.collegeClassId()).orElseThrow(() -> new RuntimeException("College class not found"));

        Registration registrationEntity = registrationMapper.toRegistration(registration, student, collegeClass);
        registrationEntity.setStatus(Status.ACTIVE);
        registrationEntity.setRegistrationDate(LocalDate.now());
        registrationEntity.setSemester(1);
        return registrationMapper.toRegistrationBaseResponse(registrationRepository.save(registrationEntity));
    }

    @Transactional
    public void deleteRegistration(UUID id) {
        Registration existingRegistration = registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found"));

        existingRegistration.setStatus(Status.INACTIVE);
        registrationRepository.save(existingRegistration);
    }
}
