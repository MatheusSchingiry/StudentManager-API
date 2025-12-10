package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.EditTeacherRequest;
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

    @Transactional
    public List<TeacherBaseResponse> getAllTeachers() {
        return teacherRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(teacherMapper::toTeacherBaseResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeacherBaseResponse getTeacherById(UUID id) {
        return teacherMapper.toTeacherBaseResponse(teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found")));
    }

    @Transactional
    public TeacherBaseResponse createTeacher(TeacherRequest teacher) {
        Teacher teacherEntity = teacherMapper.toTeacher(teacher);

        if(emailVerification(teacherEntity.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if(registrationNumberVerification(teacherEntity.getRegisterNumber())) {
            throw new RuntimeException("Register Number already exists");
        }

        teacherEntity.setStatus(Status.ACTIVE);
        teacherEntity.setHireDate(LocalDate.now());
        return teacherMapper.toTeacherBaseResponse(teacherRepository.save(teacherEntity));
    }

    @Transactional
    public TeacherBaseResponse updateTeacher(UUID id, EditTeacherRequest teacher) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        if(emailVerification(existingTeacher.getEmail()) && existingTeacher.getEmail().equals(teacher.email())) {
            throw new RuntimeException("Email already exists");
        }
        if(registrationNumberVerification(existingTeacher.getRegisterNumber()) && existingTeacher.getRegisterNumber().equals(teacher.registerNumber())) {
            throw new RuntimeException("Register Number already exists");
        }

        if(teacher.name() != null) { existingTeacher.setName(teacher.name());}
        if(teacher.registerNumber() != null) { existingTeacher.setRegisterNumber(teacher.registerNumber());}
        if(teacher.birthDate() != null) { existingTeacher.setBirthDate(teacher.birthDate());}
        if(teacher.email() != null) { existingTeacher.setEmail(teacher.email());}
        if(teacher.phoneNumber() != null) { existingTeacher.setPhoneNumber(teacher.phoneNumber());}
        if(teacher.specialty() != null) { existingTeacher.setSpecialty(teacher.specialty());}

        return teacherMapper.toTeacherBaseResponse(teacherRepository.save(existingTeacher));
    }

    @Transactional
    public void deleteTeacher(UUID id) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        existingTeacher.setStatus(Status.INACTIVE);
        teacherRepository.save(existingTeacher);
    }

    public boolean emailVerification(String email) {
        boolean exists = teacherRepository.existsByEmail(email);
        return exists;
    }

    public  boolean registrationNumberVerification(String registerNumber) {
        boolean exists = teacherRepository.existsByRegisterNumber(registerNumber);
        return exists;
    }
}
