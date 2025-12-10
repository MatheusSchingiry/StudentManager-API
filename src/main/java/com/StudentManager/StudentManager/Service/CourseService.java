package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.CourseRequest;
import com.StudentManager.StudentManager.DTO.Response.CourseBaseResponse;
import com.StudentManager.StudentManager.Exception.ConflictException;
import com.StudentManager.StudentManager.Exception.NotFoundException;
import com.StudentManager.StudentManager.Mapper.CourseMapper;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Subject;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import com.StudentManager.StudentManager.Repository.SubjectRepository;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final SubjectRepository subjectRepository;
    private final UnitRepository unitRepository;
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, SubjectRepository subjectRepository, UnitRepository unitRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.subjectRepository = subjectRepository;
        this.unitRepository = unitRepository;
        this.courseMapper = courseMapper;
    }

    @Transactional
    public Page<CourseBaseResponse> getAllCourses(Pageable pageable) {
        return courseRepository.findAllByStatus(Status.ACTIVE, pageable)
                .map(courseMapper::toCourseBaseResponse);
    }

    @Transactional
    public CourseBaseResponse getCourseById(UUID id) {
        return courseMapper.toCourseBaseResponse(courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found")));
    }

    @Transactional
    public CourseBaseResponse createCourse(CourseRequest course) {
        List<Unit> units = new ArrayList<>();

        for(var id : course.unitId()) {
            Unit unit = unitRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Unit not found with id: " + id));
            units.add(unit);
        }

        Course courseEntity = courseMapper.toCourse(course, units);
        courseEntity.setStatus(Status.ACTIVE);
        return courseMapper.toCourseBaseResponse(courseRepository.save(courseEntity));
    }

    @Transactional
    public CourseBaseResponse updateCourse(UUID id, CourseRequest courseDetails) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        if(courseDetails.name() != null) { course.setName(courseDetails.name());}
        if(courseDetails.description() != null) { course.setDescription(courseDetails.description());}
        if(courseDetails.workload() != null) { course.setWorkload(courseDetails.workload());}

        return courseMapper.toCourseBaseResponse(courseRepository.save(course));
    }

    @Transactional
    public void deleteCourse(UUID id) {
        Course existingCourse = courseRepository.findById(id).orElseThrow(() -> new NotFoundException("Course not found"));

        boolean hasActiveClasses = existingCourse.getCollegeClasses()
                .stream()
                .anyMatch(collegeClass -> collegeClass.getStatus() == Status.ACTIVE);

        if (hasActiveClasses) {
            throw new ConflictException("Cannot delete Course with active College Classes");
        }

        existingCourse.setStatus(Status.INACTIVE);
        courseRepository.save(existingCourse);
    }

    public void addSubjectToCourse(UUID courseId, UUID subjectId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));

        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new NotFoundException("Subject not found"));

        course.getSubjects().add(subject);

        courseRepository.save(course);
    }
}
