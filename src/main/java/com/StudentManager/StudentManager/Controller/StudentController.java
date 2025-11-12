package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.Request.StudentRequest;
import com.StudentManager.StudentManager.DTO.Response.StudentBaseResponse;
import com.StudentManager.StudentManager.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) { this.studentService = studentService;}

    @GetMapping
    public ResponseEntity<List<StudentBaseResponse>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentBaseResponse> getStudentById(@PathVariable UUID id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody StudentRequest student) {
        studentService.createStudent(student);
        return ResponseEntity.status(201).body("Student created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable UUID id, @RequestBody StudentRequest student) {
        studentService.updateStudent(id, student);
        return ResponseEntity.status(200).body("Student updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable UUID id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(200).body("Student deleted successfully");
    }
}
