package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.StudentRequest;
import com.StudentManager.StudentManager.DTO.StudentResponse;
import com.StudentManager.StudentManager.Model.Student;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StudentMapper {

    public static Student toStudent(StudentRequest studentRequest) {
        return Student
                .builder()
                .name(studentRequest.name())
                .registerNumber(studentRequest.registerNumber())
                .address(studentRequest.address())
                .birthDate(studentRequest.birthDate())
                .email(studentRequest.email())
                .phoneNumber(studentRequest.phoneNumber())
                .status(studentRequest.status())
                .build();
    }

    public static StudentResponse toStudentResponse(Student student) {
        return StudentResponse
                .builder()
                .id(student.getId())
                .name(student.getName())
                .registerNumber(student.getRegisterNumber())
                .address(student.getAddress())
                .birthDate(student.getBirthDate())
                .email(student.getEmail())
                .phoneNumber(student.getPhoneNumber())
                .status(student.getStatus())
                .createdAt(student.getCreatedAt())
                .updatedAt(student.getUpdatedAt())
                .build();
    }
}
