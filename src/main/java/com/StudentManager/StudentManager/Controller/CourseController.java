package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.CourseRequest;
import com.StudentManager.StudentManager.DTO.CourseResponse;
import com.StudentManager.StudentManager.Service.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<CourseResponse> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseResponse getCourseById(@PathVariable UUID id) {
        return courseService.getCourseById(id);
    }

    @PostMapping
    public CourseResponse createCourse(@RequestBody CourseRequest course) {
        return courseService.createCourse(course);
    }

    @PutMapping("/{id}")
    public CourseResponse updateCourse(@PathVariable UUID id, @RequestBody CourseRequest course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable UUID id) {
        courseService.deleteCourse(id);
    }
}
