package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.CourseRequest;
import com.StudentManager.StudentManager.DTO.Response.CourseBaseResponse;
import com.StudentManager.StudentManager.Mapper.CourseMapper;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import jakarta.transaction.Transactional;
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

    public List<CourseBaseResponse> getAllCourses() {
        return courseRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(courseMapper::toCourseBaseResponse)
                .collect(Collectors.toList());
    }

    public CourseBaseResponse getCourseById(UUID id) {
        return courseMapper.toCourseBaseResponse(courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found")));
    }

    @Transactional
    public CourseBaseResponse createCourse(CourseRequest course) {
        Course courseEntity = courseMapper.toCourse(course);
        courseEntity.setStatus(Status.ACTIVE);
        return courseMapper.toCourseBaseResponse(courseRepository.save(courseEntity));
    }

    @Transactional
    public CourseBaseResponse updateCourse(UUID id, CourseRequest courseDetails) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        if(courseDetails.name() != null) { course.setName(courseDetails.name());}
        if(courseDetails.description() != null) { course.setDescription(courseDetails.description());}
        if(courseDetails.workload() != null) { course.setWorkload(courseDetails.workload());}

        return courseMapper.toCourseBaseResponse(courseRepository.save(course));
    }

    @Transactional
    public void deleteCourse(UUID id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        boolean hasActiveClasses = existingCourse.getCollegeClasses()
                .stream()
                .anyMatch(collegeClass -> collegeClass.getStatus() == Status.ACTIVE);

        if (hasActiveClasses) {
            throw new RuntimeException("Cannot delete Course with active College Classes");
        }

        existingCourse.setStatus(Status.INACTIVE);
        courseRepository.save(existingCourse);
    }
}
