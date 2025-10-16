package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.CourseRequest;
import com.StudentManager.StudentManager.DTO.CourseResponse;
import com.StudentManager.StudentManager.Mapper.CourseMapper;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(courseMapper::toCourseResponse)
                .collect(Collectors.toList());
    }

    public CourseResponse getCourseById(UUID id) {
        return courseMapper.toCourseResponse(courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found")));
    }

    public CourseResponse createCourse(CourseRequest course) {
        Course newCourse = courseMapper.toCourse(course);
        courseRepository.save(newCourse);

        return courseMapper.toCourseResponse(newCourse);
    }

    public CourseResponse updateCourse(UUID id, CourseRequest courseDetails) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));


        if(courseDetails.name() != null) { course.setName(courseDetails.name());}
        if(courseDetails.description() != null) { course.setDescription(courseDetails.description());}
        if(courseDetails.workload() != null) { course.setWorkload(courseDetails.workload());}

        courseRepository.save(course);
        return courseMapper.toCourseResponse(course);
    }

    public void deleteCourse(UUID id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));
        existingCourse.setStatus(Status.INACTIVE);
        courseRepository.save(existingCourse);
    }
}
