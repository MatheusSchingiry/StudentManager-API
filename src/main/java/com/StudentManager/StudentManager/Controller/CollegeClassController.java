package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.Request.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.Response.CollegeClassBaseResponse;
import com.StudentManager.StudentManager.Service.CollegeClassService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/college-classes")
public class CollegeClassController {

    private final CollegeClassService collegeClassService;

    public CollegeClassController(CollegeClassService collegeClassService) { this.collegeClassService = collegeClassService; }

    @GetMapping
    public ResponseEntity<Page<CollegeClassBaseResponse>> getAllCollegeClasses(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(collegeClassService.getAllCollegeClasses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CollegeClassBaseResponse> getCollegeClassById(@PathVariable UUID id) {
        return ResponseEntity.ok(collegeClassService.getCollegeClassById(id));
    }

    @PostMapping()
    public ResponseEntity<String> createCollegeClass(@RequestBody @Valid CollegeClassRequest collegeClass) {
        collegeClassService.createCollegeClass(collegeClass);
        return ResponseEntity.status(201).body("College class created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCollegeClass(@PathVariable UUID id) {
        collegeClassService.deleteCollegeClass(id);
        return ResponseEntity.status(200).body("College class deleted successfully");
    }
}
