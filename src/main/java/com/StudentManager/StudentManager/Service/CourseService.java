package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) { this.courseRepository = courseRepository; }

    public List<Course> getAllCourses() { return courseRepository.findAllByStatus(Status.ACTIVE);}

    public Course getCourseById(UUID id) { return courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found")); }

    @Transactional
    public Course createCourse(Course course) {
        course.setStatus(Status.ACTIVE);
        return courseRepository.save(course);
    }

    @Transactional
    public Course updateCourse(UUID id, Course courseDetails) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course not found"));

        if(courseDetails.getName() != null) { course.setName(courseDetails.getName());}
        if(courseDetails.getDescription() != null) { course.setDescription(courseDetails.getDescription());}
        if(courseDetails.getWorkload() != null) { course.setWorkload(courseDetails.getWorkload());}

        return courseRepository.save(course);
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
