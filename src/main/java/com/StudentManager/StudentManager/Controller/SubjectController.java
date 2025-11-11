package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Subject;;
import com.StudentManager.StudentManager.Service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<String> createSubject(@RequestBody Subject subject) {
        subjectService.createSubject(subject);
        return ResponseEntity.status(201).body("Subject created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable UUID id, @RequestBody Subject subject) {
        subjectService.updateSubject(id, subject);
        return ResponseEntity.status(200).body("Subject updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(200).body("Subject deleted successfully");
    }
}
