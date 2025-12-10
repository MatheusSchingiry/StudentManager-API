package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.Request.SubjectRequest;
import com.StudentManager.StudentManager.DTO.Response.SubjectBaseResponse;
import com.StudentManager.StudentManager.Service.SubjectService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @GetMapping
    public ResponseEntity<Page<SubjectBaseResponse>> getAllSubjects(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(subjectService.getAllSubjects(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectBaseResponse> getSubjectById(@PathVariable UUID id) {
        return ResponseEntity.ok(subjectService.getSubjectById(id));
    }

    @PostMapping
    public ResponseEntity<String> createSubject(@RequestBody @Valid SubjectRequest subject) {
        subjectService.createSubject(subject);
        return ResponseEntity.status(201).body("Subject created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateSubject(@PathVariable UUID id, @RequestBody @Valid SubjectRequest subject) {
        subjectService.updateSubject(id, subject);
        return ResponseEntity.status(200).body("Subject updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable UUID id) {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(200).body("Subject deleted successfully");
    }
}
