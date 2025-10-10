package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.StudentRequest;
import com.StudentManager.StudentManager.DTO.StudentResponse;
import com.StudentManager.StudentManager.Mapper.StudentMapper;
import com.StudentManager.StudentManager.Model.Enum.StudentStatus;
import com.StudentManager.StudentManager.Model.Student;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toStudentResponse)
                .collect(Collectors.toList());
    }

    public StudentResponse getStudentByEmail(String email) {
        return studentRepository.findByEmail(email).map(StudentMapper::toStudentResponse).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public StudentResponse getStudentByRegisterNumber(Long registerNumber) {
        return studentRepository.findByRegisterNumber(registerNumber).map(StudentMapper::toStudentResponse).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public StudentResponse createStudent(StudentRequest student) {
        Student newStudent = StudentMapper.toStudent(student);

        newStudent.setStatus(StudentStatus.ACTIVE);
        studentRepository.save(newStudent);

        return StudentMapper.toStudentResponse(newStudent);
    }

    public StudentResponse editStudentById(UUID id, StudentRequest student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        Student correctedStudent = StudentMapper.toStudent(student);

        if(correctedStudent.getName() == null){ correctedStudent.setName(existingStudent.getName()); }
        if(correctedStudent.getRegisterNumber() == null){ correctedStudent.setRegisterNumber(existingStudent.getRegisterNumber()); }
        if(correctedStudent.getAddress() == null){ correctedStudent.setAddress(existingStudent.getAddress()); }
        if(correctedStudent.getBirthDate() == null){ correctedStudent.setBirthDate(existingStudent.getBirthDate()); }
        if(correctedStudent.getEmail() == null){ correctedStudent.setEmail(existingStudent.getEmail()); }
        if(correctedStudent.getPhoneNumber() == null){ correctedStudent.setPhoneNumber(existingStudent.getPhoneNumber()); }
        if(correctedStudent.getStatus() == null){ correctedStudent.setStatus(existingStudent.getStatus()); }

        studentRepository.save(correctedStudent);
        return StudentMapper.toStudentResponse(correctedStudent);
    }

    public void deleteStudentById(UUID id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));
        student.setStatus(StudentStatus.INACTIVE);
    }
}
