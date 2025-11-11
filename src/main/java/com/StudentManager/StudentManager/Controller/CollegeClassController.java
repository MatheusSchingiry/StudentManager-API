package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Service.CollegeClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/college-classes")
public class CollegeClassController {

    private final CollegeClassService collegeClassService;

    public CollegeClassController(CollegeClassService collegeClassService) { this.collegeClassService = collegeClassService; }

    @GetMapping
    public ResponseEntity<List<CollegeClass>> getAllCollegeClasses() {
        return ResponseEntity.ok(collegeClassService.getAllCollegeClasses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollegeClass> getCollegeClassById(@PathVariable UUID id) {
        return ResponseEntity.ok(collegeClassService.getCollegeClassById(id));
    }

    @PostMapping()
    public ResponseEntity<String> createCollegeClass(@RequestBody CollegeClass collegeClass) {
        collegeClassService.createCollegeClass(collegeClass);
        return ResponseEntity.status(201).body("College class created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCollegeClass(@PathVariable UUID id) {
        collegeClassService.deleteCollegeClass(id);
        return ResponseEntity.status(200).body("College class deleted successfully");
    }
}
