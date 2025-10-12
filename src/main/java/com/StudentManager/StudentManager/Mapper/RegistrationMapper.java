package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.RegistrationResponse;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {

    private final StudentRepository studentRepository;

    public Registration toRegistration(RegistrationRequest registrationRequest) {
        return Registration
                .builder()
                .student(studentRepository.findById(registrationRequest.studentId()).orElseThrow(() -> new RuntimeException("Student not found")))
                .status(registrationRequest.status())
                .registrationDate(registrationRequest.registrationDate())
                .semester(registrationRequest.semester())
                .build();
    }

    public RegistrationResponse toRegistrationResponse(Registration  registration) {
        return RegistrationResponse
                .builder()
                .id(registration.getId())
                .student(registration.getStudent())
                .status(registration.getStatus())
                .registrationDate(registration.getRegistrationDate())
                .semester(registration.getSemester())
                .createdAt(registration.getCreatedAt())
                .updatedAt(registration.getUpdatedAt())
                .build();
    }
}
