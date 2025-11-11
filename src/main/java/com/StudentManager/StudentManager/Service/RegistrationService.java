package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Repository.RegistrationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RegistrationService {

    private final RegistrationRepository registrationRepository;

    public RegistrationService(RegistrationRepository registrationRepository) { this.registrationRepository = registrationRepository;}

    public List<Registration> getAllRegistrations(){ return registrationRepository.findAll(); }

    public Registration getRegistrationById(UUID id) { return registrationRepository.findById(id).orElseThrow(() -> new RuntimeException("Registration not found")); }

    @Transactional
    public Registration createRegistration(Registration registration) {
        registration.setStatus(Status.ACTIVE);
        return registrationRepository.save(registration);
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
