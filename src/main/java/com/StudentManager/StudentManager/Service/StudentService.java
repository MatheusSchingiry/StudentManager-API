package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.EditStudentRequest;
import com.StudentManager.StudentManager.DTO.Request.StudentRequest;
import com.StudentManager.StudentManager.DTO.Response.StudentBaseResponse;
import com.StudentManager.StudentManager.Exception.ConflictException;
import com.StudentManager.StudentManager.Exception.NotFoundException;
import com.StudentManager.StudentManager.Mapper.StudentMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Student;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public Page<StudentBaseResponse> getAllStudents(Pageable pageable) {
        return studentRepository.findAllByStatus(Status.ACTIVE, pageable)
                .map(studentMapper::toStudentBaseResponse);
    }

    public StudentBaseResponse getStudentById(UUID id) {
        return studentMapper.toStudentBaseResponse(studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found")));
    }

    @Transactional
    public StudentBaseResponse createStudent(StudentRequest student) {
        Student studentEntity = studentMapper.toStudent(student);

        if(emailVerification(studentEntity.getEmail())) {
            throw new ConflictException("Email already exists");
        }
        if(registrationNumberVerification(studentEntity.getRegisterNumber())) {
            throw new ConflictException("Register Number already exists");
        }

        studentEntity.setStatus(Status.ACTIVE);
        return studentMapper.toStudentBaseResponse(studentRepository.save(studentEntity));
    }

    @Transactional
    public StudentBaseResponse updateStudent(UUID id, EditStudentRequest student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));

        if(emailVerification(existingStudent.getEmail()) && existingStudent.getEmail().equals(student.email())) {
            throw new ConflictException("Email already exists");
        }
        if(registrationNumberVerification(existingStudent.getRegisterNumber()) && existingStudent.getRegisterNumber().equals(student.registerNumber())) {
            throw new ConflictException("Register Number already exists");
        }

        if(student.name() != null){ existingStudent.setName(student.name()); }
        if(student.registerNumber() != null){ existingStudent.setRegisterNumber(student.registerNumber()); }
        if(student.birthDate() != null){ existingStudent.setBirthDate(student.birthDate()); }
        if(student.address() != null){ existingStudent.setAddress(student.address()); }
        if(student.email() != null){ existingStudent.setEmail(student.email()); }
        if(student.phoneNumber() != null){ existingStudent.setPhoneNumber(student.phoneNumber()); }

        return studentMapper.toStudentBaseResponse(studentRepository.save(existingStudent));
    }

    @Transactional
    public void deleteStudent(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new NotFoundException("Student not found"));

        boolean hasActiveRegistrations = student.getRegistrations()
                .stream()
                .anyMatch(registration -> registration.getStatus() == Status.ACTIVE);

        if (hasActiveRegistrations) {
            throw new ConflictException("Cannot delete student with active registrations");
        }

        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }

    public boolean emailVerification(String email) {
        boolean exists = studentRepository.existsByEmail(email);
        return exists;
    }

    public  boolean registrationNumberVerification(String registerNumber) {
        boolean exists = studentRepository.existsByRegisterNumber(registerNumber);
        return exists;
    }
}
