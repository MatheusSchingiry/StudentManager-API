package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Subject;;
import com.StudentManager.StudentManager.Service.SubjectService;
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
    public List<Subject> getAllSubjects() { return subjectService.getAllSubjects(); }

    @GetMapping("/{id}")
    public Subject getSubjectById(@PathVariable UUID id) { return subjectService.getSubjectById(id); }

    @PostMapping
    public Subject createSubject(@RequestBody Subject subject) { return subjectService.createSubject(subject); }

    @PutMapping("/{id}")
    public Subject updateSubject(@PathVariable UUID id, @RequestBody Subject subject) { return subjectService.updateSubject(id, subject); }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable UUID id) { subjectService.deleteSubject(id); }
}
