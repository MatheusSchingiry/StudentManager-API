package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.StudentRequest;
import com.StudentManager.StudentManager.DTO.Response.StudentBaseResponse;
import com.StudentManager.StudentManager.Mapper.StudentMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Student;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentService(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    public List<StudentBaseResponse> getAllStudents() {
        return studentRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(studentMapper::toStudentBaseResponse)
                .collect(Collectors.toList());
    }

    public StudentBaseResponse getStudentById(UUID id) {
        return studentMapper.toStudentBaseResponse(studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found")));
    }

    @Transactional
    public StudentBaseResponse createStudent(StudentRequest student) {
        Student studentEntity = studentMapper.toStudent(student);
        studentEntity.setStatus(Status.ACTIVE);
        return studentMapper.toStudentBaseResponse(studentRepository.save(studentEntity));
    }

    @Transactional
    public StudentBaseResponse updateStudent(UUID id, StudentRequest student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

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
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        boolean hasActiveRegistrations = student.getRegistrations()
                .stream()
                .anyMatch(registration -> registration.getStatus() == Status.ACTIVE);

        if (hasActiveRegistrations) {
            throw new RuntimeException("Cannot delete student with active registrations");
        }

        student.setStatus(Status.INACTIVE);
        studentRepository.save(student);
    }
}
