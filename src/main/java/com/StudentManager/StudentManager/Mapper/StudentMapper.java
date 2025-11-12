package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.StudentRequest;
import com.StudentManager.StudentManager.DTO.Response.StudentBaseResponse;
import com.StudentManager.StudentManager.Model.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    public Student toStudent(StudentRequest studentRequest) {
        return Student.builder()
                .name(studentRequest.name())
                .registerNumber(studentRequest.registerNumber())
                .birthDate(java.time.LocalDate.parse(studentRequest.birthDate()))
                .address(studentRequest.address())
                .email(studentRequest.email())
                .phoneNumber(studentRequest.phoneNumber())
                .build();
    }

    public StudentBaseResponse toStudentBaseResponse(Student student) {
        return StudentBaseResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .registerNumber(student.getRegisterNumber())
                .birthDate(student.getBirthDate().toString())
                .address(student.getAddress())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .status(student.getStatus())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
