package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.RegistrationResponse;
import com.StudentManager.StudentManager.Mapper.RegistrationMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Repository.RegistrationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;
    private final RegistrationMapper registrationMapper;

    public RegistrationService(RegistrationRepository registrationRepository, RegistrationMapper registrationMapper) {
        this.registrationRepository = registrationRepository;
        this.registrationMapper = registrationMapper;
    }

    public List<RegistrationResponse> getAllRegistration(){
        return registrationRepository.findAll().stream().map(registrationMapper::toRegistrationResponse).collect(Collectors.toList());
    }

    public RegistrationResponse getRegistrationById(UUID id) {
        return registrationMapper.toRegistrationResponse(registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found")));
    }

    public List<RegistrationResponse> getRegistrationByStudentId(UUID studentId) {
        return registrationRepository.findByStudentId(studentId)
                .stream()
                .map(registrationMapper::toRegistrationResponse)
                .collect(Collectors.toList());
    }

    public List<RegistrationResponse> getRegistrationByCollegeClassId(UUID collegeClassId) {
        return registrationRepository.findByCollegeClassId(collegeClassId)
                .stream()
                .map(registrationMapper::toRegistrationResponse)
                .collect(Collectors.toList());
    }

    public RegistrationResponse createRegistration(RegistrationRequest registration) {
        Registration newRegistration = registrationMapper.toRegistration(registration);
        registrationRepository.save(newRegistration);
        return registrationMapper.toRegistrationResponse(newRegistration);
    }

    public RegistrationResponse editRegistrationById(UUID id, RegistrationRequest registration) {
        Registration existingRegistration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        if(registration.status() != null){ existingRegistration.setStatus(registration.status()); }
        if(registration.registrationDate() != null){ existingRegistration.setRegistrationDate(registration.registrationDate()); }
        if(registration.semester() != null){ existingRegistration.setSemester(registration.semester()); }

        registrationRepository.save(existingRegistration);
        return registrationMapper.toRegistrationResponse(existingRegistration);
    }

    public void deleteRegistrationById(UUID id) {
        Registration existingRegistration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        existingRegistration.setStatus(Status.INACTIVE);
        registrationRepository.save(existingRegistration);
    }
}
