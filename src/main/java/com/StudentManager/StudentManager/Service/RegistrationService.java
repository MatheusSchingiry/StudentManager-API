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

    public RegistrationResponse getRegistrationByStudentId(UUID studentId) {
        return registrationRepository.findAll().stream()
                .filter(registration -> registration.getStudent().getId().equals(studentId))
                .findFirst()
                .map(registrationMapper::toRegistrationResponse)
                .orElseThrow(() -> new RuntimeException("Registration not found for student id: " + studentId));
    }

    public RegistrationResponse createRegistration(RegistrationRequest registration) {
        Registration newRegistration = registrationMapper.toRegistration(registration);
        registrationRepository.save(newRegistration);
        return registrationMapper.toRegistrationResponse(newRegistration);
    }

    public RegistrationResponse editRegistrationById(UUID id, RegistrationRequest registration) {
        Registration existingRegistration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));

        Registration updatedRegistration = registrationMapper.toRegistration(registration);

        existingRegistration.setId(id);
        if(updatedRegistration.getStudent() != null){ updatedRegistration.setStudent(existingRegistration.getStudent()); }
        if(updatedRegistration.getStatus() != null){ updatedRegistration.setStatus(existingRegistration.getStatus()); }
        if(updatedRegistration.getRegistrationDate() != null){ updatedRegistration.setRegistrationDate(existingRegistration.getRegistrationDate()); }
        if(updatedRegistration.getSemester() != null){ updatedRegistration.setSemester(existingRegistration.getSemester()); }

        registrationRepository.save(updatedRegistration);
        return registrationMapper.toRegistrationResponse(updatedRegistration);
    }

    public void deleteRegistrationById(UUID id) {
        Registration existingRegistration = registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
        existingRegistration.setStatus(Status.INACTIVE);
        registrationRepository.save(existingRegistration);
    }
}
