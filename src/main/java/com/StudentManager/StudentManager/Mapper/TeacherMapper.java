package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.TeacherRequest;
import com.StudentManager.StudentManager.DTO.Response.TeacherBaseResponse;
import com.StudentManager.StudentManager.Model.Teacher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    public Teacher toTeacher(TeacherRequest teacherRequest) {
        return Teacher.builder()
                .name(teacherRequest.name())
                .registerNumber(teacherRequest.registerNumber())
                .birthDate(teacherRequest.birthDate())
                .address(teacherRequest.address())
                .email(teacherRequest.email())
                .phoneNumber(teacherRequest.phoneNumber())
                .specialty(teacherRequest.specialty())
                .build();
    }

    public TeacherBaseResponse toTeacherBaseResponse(Teacher teacher) {
        return TeacherBaseResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .registerNumber(teacher.getRegisterNumber())
                .birthDate(teacher.getBirthDate())
                .address(teacher.getAddress())
                .email(teacher.getEmail())
                .phoneNumber(teacher.getPhoneNumber())
                .specialty(teacher.getSpecialty())
                .hireDate(teacher.getHireDate())
                .unitsNames(teacher.getUnits().stream().map(unit -> unit.getName()).collect(Collectors.toSet()))
                .subjectsNames(teacher.getSubjects().stream().map(subject -> subject.getName()).collect(Collectors.toSet()))
                .status(teacher.getStatus())
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())
                .build();
    }
}
