package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.Response.RegistrationBaseResponse;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrationMapper {

    private final CollegeClassMapper collegeClassMapper;

    public Registration toRegistration(RegistrationRequest registrationRequest, Student student, CollegeClass collegeClass) {
        return Registration.builder()
                .semester(registrationRequest.semester())
                .student(student)
                .collegeClass(collegeClass)
                .build();
    }

    public RegistrationBaseResponse toRegistrationBaseResponse(Registration registration) {
        return RegistrationBaseResponse.builder()
                .id(registration.getId())
                .semester(registration.getSemester())
                .registrationDate(registration.getRegistrationDate())
                .studentName(registration.getStudent().getName())
                .collegeClass(collegeClassMapper.toCollegeClassRegistrationResponse(registration.getCollegeClass()))
                .status(registration.getStatus())
                .createdAt(registration.getCreatedAt())
                .updatedAt(registration.getUpdatedAt())
                .build();
    }
}
