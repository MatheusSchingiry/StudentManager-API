package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Service.CollegeClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/college-classes")
public class CollegeClassController {

    private final CollegeClassService collegeClassService;

    public CollegeClassController(CollegeClassService collegeClassService) { this.collegeClassService = collegeClassService; }

    @GetMapping
    public List<CollegeClass> getAllCollegeClasses() { return collegeClassService.getAllCollegeClasses(); }

    @GetMapping("/{id}")
    public CollegeClass getCollegeClassById(@PathVariable UUID id) { return collegeClassService.getCollegeClassById(id); }

    @PostMapping()
    public CollegeClass createCollegeClass(@RequestBody CollegeClass collegeClass) { return collegeClassService.createCollegeClass(collegeClass); }

    @DeleteMapping("/{id}")
    public void deleteCollegeClass(@PathVariable UUID id) { collegeClassService.deleteCollegeClass(id); }
}
