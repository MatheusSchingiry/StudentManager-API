package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.StudentRequest;
import com.StudentManager.StudentManager.DTO.StudentResponse;
import com.StudentManager.StudentManager.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/email/{email}")
    public StudentResponse getStudentByEmail(@PathVariable String email) {
        return studentService.getStudentByEmail(email);
    }

    @GetMapping("/registerNumber/{registerNumber}")
    public StudentResponse getStudentByRegisterNumber(@PathVariable Long registerNumber) {
        return studentService.getStudentByRegisterNumber(registerNumber);
    }

    @PostMapping
    public StudentResponse createStudent(@RequestBody StudentRequest student) {
        return studentService.createStudent(student);
    }

    @PutMapping("/{id}")
    public StudentResponse editStudentById(@PathVariable UUID id, @RequestBody StudentRequest student) {
        return studentService.editStudentById(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable UUID id) {
        studentService.deleteStudentById(id);
    }
}
