package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.Response.RegistrationBaseResponse;
import com.StudentManager.StudentManager.Mapper.RegistrationMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        Registration registrationEntity = registrationMapper.toRegistration(registration);
        registrationEntity.setStatus(Status.ACTIVE);
        registrationEntity.setRegistrationDate(LocalDate.now());
        return registrationMapper.toRegistrationBaseResponse(registrationRepository.save(registrationEntity));
    }

    public Registration advanceSemester(UUID id) {
        Registration existingRegistration = registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found"));

        existingRegistration.setSemester(existingRegistration.getSemester()+1);

        return registrationRepository.save(existingRegistration);
    }

    @Transactional
    public void deleteRegistration(UUID id) {
        Registration existingRegistration = registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found"));

        existingRegistration.setStatus(Status.INACTIVE);
        registrationRepository.save(existingRegistration);
    }
}
