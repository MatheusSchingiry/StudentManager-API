package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.TeacherRequest;
import com.StudentManager.StudentManager.DTO.Response.TeacherBaseResponse;
import com.StudentManager.StudentManager.Model.Teacher;
import com.StudentManager.StudentManager.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TeacherMapper {

    private final TeacherRepository teacherRepository;

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
        List<Teacher> unitsAndSubjects = teacherRepository.findUnitsAndSubjectsIdByTeacherId(teacher.getId());

        List<String> unitsNames = unitsAndSubjects.stream()
                .flatMap(t -> t.getUnits().stream())
                .map(unit -> unit.getName())
                .distinct()
                .collect(Collectors.toList());

        List<String> subjectsNames = unitsAndSubjects.stream()
                .flatMap(t -> t.getSubjects().stream())
                .map(subject -> subject.getName())
                .distinct()
                .collect(Collectors.toList());


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
                .unitsNames(unitsNames)
                .subjectsNames(subjectsNames)
                .status(teacher.getStatus())
                .createdAt(teacher.getCreatedAt())
                .updatedAt(teacher.getUpdatedAt())
                .build();
    }
}
