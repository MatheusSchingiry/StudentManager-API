package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Student;
import com.StudentManager.StudentManager.Repository.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAllByStatus(Status.ACTIVE);
    }

    public Student getStudentById(UUID id) { return studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));}

    @Transactional
    public Student createStudent(Student student) {
        student.setStatus(Status.ACTIVE);
        return studentRepository.save(student);
    }

    @Transactional
    public Student updateStudent(UUID id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        if(student.getName() != null){ existingStudent.setName(student.getName()); }
        if(student.getRegisterNumber() != null){ existingStudent.setRegisterNumber(student.getRegisterNumber()); }
        if(student.getBirthDate() != null){ existingStudent.setBirthDate(student.getBirthDate()); }
        if(student.getAddress() != null){ existingStudent.setAddress(student.getAddress()); }
        if(student.getEmail() != null){ existingStudent.setEmail(student.getEmail()); }
        if(student.getPhoneNumber() != null){ existingStudent.setPhoneNumber(student.getPhoneNumber()); }

        return studentRepository.save(existingStudent);
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
