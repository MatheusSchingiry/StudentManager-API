package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.CollegeClassResponse;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Enum.Period;
import com.StudentManager.StudentManager.Service.CollegeClassService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/college-classes")
public class CollegeClassController {

    private final CollegeClassService collegeClassService;

    public CollegeClassController(CollegeClassService collegeClassService) {
        this.collegeClassService = collegeClassService;
    }

    @GetMapping
    public List<CollegeClassResponse> getAllCollegeClasses() {
        return collegeClassService.getAllCollegeClasses();
    }

    @GetMapping("/{id}")
    public CollegeClassResponse getCollegeClassById(@PathVariable UUID id) {
        return collegeClassService.getCollegeClassById(id);
    }

    @GetMapping("/period/{period}")
    public List<CollegeClassResponse> getCollegeClassByPeriod(@PathVariable Period period) {
        return collegeClassService.getCollegeClassByPeriod(period);
    }

    @GetMapping("/registration/{registrationId}")
    public List<CollegeClassResponse> getCollegeClassByRegistrationId(@PathVariable UUID registrationId) {
        return collegeClassService.getCollegeClassByRegistrationId(registrationId);
    }

    @PostMapping()
    public CollegeClassResponse createCollegeClass(@RequestBody CollegeClassRequest collegeClass) {
        return collegeClassService.createCollegeClass(collegeClass);
    }

    @PutMapping("/{id}")
    public CollegeClassResponse editCollegeClassById(@PathVariable UUID id, @RequestBody CollegeClassRequest collegeClass) {
        return collegeClassService.editCollegeClass(id, collegeClass);
    }

    @DeleteMapping("/{id}")
    public void deleteCollegeClassById(@PathVariable UUID id) {
        collegeClassService.deleteCollegeClass(id);
    }
}
