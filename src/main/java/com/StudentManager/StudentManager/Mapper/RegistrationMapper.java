package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.Response.RegistrationBaseResponse;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Repository.CollegeClassRepository;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {

    private final StudentRepository studentRepository;
    private final CollegeClassRepository collegeClassRepository;
    private final CollegeClassMapper collegeClassMapper;

    public Registration toRegistration(RegistrationRequest registrationRequest) {
        return Registration.builder()
                .semester(registrationRequest.semester())
                .student(studentRepository.findById(registrationRequest.studentId()).orElseThrow(() -> new RuntimeException("Student not found")))
                .collegeClass(collegeClassRepository.findById(registrationRequest.collegeClassId()).orElseThrow(() -> new RuntimeException("College class not found")))
                .build();
    }

    public RegistrationBaseResponse toRegistrationBaseResponse(Registration registration) {
        return RegistrationBaseResponse.builder()
                .id(registration.getId())
                .semester(registration.getSemester())
                .registrationDate(registration.getRegistrationDate())
                .studentName(registration.getStudent().getName())
                .collegeClass(collegeClassMapper.toCollegeClassRegistrationResponse(registration.getCollegeClass()))
                .build();
    }
}
