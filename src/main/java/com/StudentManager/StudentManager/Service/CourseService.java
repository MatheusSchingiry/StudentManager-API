package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.CourseRequest;
import com.StudentManager.StudentManager.DTO.CourseResponse;
import com.StudentManager.StudentManager.Mapper.CourseMapper;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final UnitRepository unitRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, UnitRepository unitRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.unitRepository = unitRepository;
        this.courseMapper = courseMapper;
    }

    @Transactional
    public List<CourseResponse> getAllCourses() {
        return courseRepository.findAllWithUnits()
                .stream()
                .map(courseMapper::toCourseResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CourseResponse getCourseById(UUID id) {
        Course course = courseRepository.findByIdWithUnits(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return courseMapper.toCourseResponse(course);
    }

    @Transactional
    public CourseResponse createCourse(CourseRequest course) {
        Course newCourse = courseMapper.toCourse(course);

        if (course.unitId() != null) {
            Unit unit = unitRepository.findById(course.unitId())
                    .orElseThrow(() -> new RuntimeException("Unit not found with id: " + course.unitId()));

            if (newCourse.getUnits() == null) {
                newCourse.setUnits(new HashSet<>());
            }

            newCourse.getUnits().add(unit);

            if (unit.getCourses() == null) {
                unit.setCourses(new HashSet<>());
            }
            unit.getCourses().add(newCourse);
        }
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
