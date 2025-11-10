package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Student;
import com.StudentManager.StudentManager.Service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService;}

    @GetMapping
    public List<Student> getAllStudents() { return studentService.getAllStudents(); }

    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable UUID id) { return studentService.getStudentById(id); }

    @PostMapping
    public Student createStudent(@RequestBody Student student) { return studentService.createStudent(student); }

    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable UUID id, @RequestBody Student student) { return studentService.updateStudent(id, student); }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable UUID id) { studentService.deleteStudent(id); }
}
