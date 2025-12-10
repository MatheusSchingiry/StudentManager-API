package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.Request.EditTeacherRequest;
import com.StudentManager.StudentManager.DTO.Request.TeacherRequest;
import com.StudentManager.StudentManager.DTO.Response.TeacherBaseResponse;
import com.StudentManager.StudentManager.Service.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService;}

    @GetMapping
    public ResponseEntity<List<TeacherBaseResponse>> getAllTeachers() {
        return ResponseEntity.ok(teacherService.getAllTeachers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherBaseResponse> getTeacherById(@PathVariable UUID id) {
        return ResponseEntity.ok(teacherService.getTeacherById(id));
    }

    @PostMapping
    public ResponseEntity<String> createTeacher(@RequestBody @Valid TeacherRequest teacher) {
        teacherService.createTeacher(teacher);
        return ResponseEntity.status(201).body("Teacher created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable UUID id, @RequestBody @Valid EditTeacherRequest teacher) {
        teacherService.updateTeacher(id, teacher);
        return ResponseEntity.status(200).body("Teacher updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.status(200).body("Teacher deleted successfully");
    }
}
