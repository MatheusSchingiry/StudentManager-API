package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.RegistrationDetailResponse;
import com.StudentManager.StudentManager.DTO.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.RegistrationResponse;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class RegistrationMapper {

    private final StudentRepository studentRepository;
    private final CollegeClassRepository collegeClassRepository;
    private final StudentMapper studentMapper;
    private final CollegeClassMapper collegeClassMapper;

    public RegistrationMapper(StudentRepository studentRepository, CollegeClassRepository collegeClassRepository, StudentMapper studentMapper, @Lazy CollegeClassMapper collegeClassMapper) {
        this.studentRepository = studentRepository;
        this.collegeClassRepository = collegeClassRepository;
        this.studentMapper = studentMapper;
        this.collegeClassMapper = collegeClassMapper;
    }

    public Registration toRegistration(RegistrationRequest registrationRequest) {
        return Registration
                .builder()
                .student(studentRepository.findById(registrationRequest.studentId()).orElseThrow(() -> new RuntimeException("Student not found")))
                .collegeClass(collegeClassRepository.findById(registrationRequest.collegeClassId()).orElseThrow(() -> new RuntimeException("College class not found")))
                .status(registrationRequest.status())
                .registrationDate(registrationRequest.registrationDate())
                .semester(registrationRequest.semester())
                .build();
    }

    public RegistrationResponse toRegistrationResponse(Registration  registration) {
        return RegistrationResponse
                .builder()
                .id(registration.getId())
                .student(studentMapper.toStudentResponse(registration.getStudent()))
                .collegeClass(collegeClassMapper.toCollegeClassResponse(registration.getCollegeClass()))
                .status(registration.getStatus())
                .registrationDate(registration.getRegistrationDate())
                .semester(registration.getSemester())
                .createdAt(registration.getCreatedAt())
                .updatedAt(registration.getUpdatedAt())
                .build();
    }

    public RegistrationDetailResponse toRegistrationDetailResponse(Registration registration) {
        return RegistrationDetailResponse.builder()
                .id(registration.getId())
                .student(studentMapper.toStudentResponse(registration.getStudent()))
                .status(registration.getStatus())
                .registrationDate(registration.getRegistrationDate())
                .semester(registration.getSemester())
                .build();
    }
}
