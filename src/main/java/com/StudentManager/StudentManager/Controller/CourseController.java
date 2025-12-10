package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.Request.CourseRequest;
import com.StudentManager.StudentManager.DTO.Response.CourseBaseResponse;
import com.StudentManager.StudentManager.Service.CourseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ResponseEntity<Page<CourseBaseResponse>> getAllCourses(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(courseService.getAllCourses(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseBaseResponse> getCourseById(@PathVariable UUID id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping
    public ResponseEntity<String> createCourse(@RequestBody @Valid CourseRequest course) {
        courseService.createCourse(course);
        return ResponseEntity.status(201).body("Course created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable UUID id, @RequestBody @Valid CourseRequest course) {
        courseService.updateCourse(id, course);
        return ResponseEntity.status(200).body("Course updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(200).body("Course deleted successfully");
    }
}
