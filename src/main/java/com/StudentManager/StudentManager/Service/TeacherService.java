package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.TeacherRequest;
import com.StudentManager.StudentManager.DTO.Response.TeacherBaseResponse;
import com.StudentManager.StudentManager.Mapper.TeacherMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Teacher;
import com.StudentManager.StudentManager.Repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    public TeacherService(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
    }

    public List<TeacherBaseResponse> getAllTeachers() {
        return teacherRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(teacherMapper::toTeacherBaseResponse)
                .collect(Collectors.toList());
    }

    public TeacherBaseResponse getTeacherById(UUID id) {
        return teacherMapper.toTeacherBaseResponse(teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found")));
    }

    @Transactional
    public TeacherBaseResponse createTeacher(TeacherRequest teacher) {
        Teacher teacherEntity = teacherMapper.toTeacher(teacher);
        teacherEntity.setStatus(Status.ACTIVE);
        teacherEntity.setHireDate(LocalDate.now());
        return teacherMapper.toTeacherBaseResponse(teacherRepository.save(teacherEntity));
    }

    @Transactional
    public TeacherBaseResponse updateTeacher(UUID id, TeacherRequest teacherDetail) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        if(teacherDetail.name() != null) { teacher.setName(teacherDetail.name());}
        if(teacherDetail.registerNumber() != null) { teacher.setRegisterNumber(teacherDetail.registerNumber());}
        if(teacherDetail.birthDate() != null) { teacher.setBirthDate(teacherDetail.birthDate());}
        if(teacherDetail.email() != null) { teacher.setEmail(teacherDetail.email());}
        if(teacherDetail.phoneNumber() != null) { teacher.setPhoneNumber(teacherDetail.phoneNumber());}
        if(teacherDetail.specialty() != null) { teacher.setSpecialty(teacherDetail.specialty());}

        return teacherMapper.toTeacherBaseResponse(teacherRepository.save(teacher));
    }

    @Transactional
    public void deleteTeacher(UUID id) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        existingTeacher.setStatus(Status.INACTIVE);
        teacherRepository.save(existingTeacher);
    }
}
