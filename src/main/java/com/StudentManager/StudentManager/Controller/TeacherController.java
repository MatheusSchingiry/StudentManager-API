package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Teacher;
import com.StudentManager.StudentManager.Service.TeacherService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) { this.teacherService = teacherService;}

    @GetMapping
    public List<Teacher> getAllTeachers() { return teacherService.getAllTeachers(); }

    @GetMapping("/{id}")
    public Teacher getTeacherById(@PathVariable UUID id) { return teacherService.getTeacherById(id); }

    @PostMapping
    public Teacher createTeacher(@RequestBody Teacher teacher) { return teacherService.createTeacher(teacher); }

    @PutMapping("/{id}")
    public Teacher updateTeacher(@PathVariable UUID id, @RequestBody Teacher teacher) { return teacherService.updateTeacher(id, teacher); }

    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable UUID id) { teacherService.deleteTeacher(id); }
}
