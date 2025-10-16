package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.CollegeClassDetailResponse;
import com.StudentManager.StudentManager.DTO.CourseRequest;
import com.StudentManager.StudentManager.DTO.CourseResponse;
import com.StudentManager.StudentManager.Model.Course;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private final CollegeClassMapper collegeClassMapper;

    public CourseMapper(@Lazy CollegeClassMapper collegeClassMapper) {
        this.collegeClassMapper = collegeClassMapper;
    }

    public Course toCourse(CourseRequest courseRequest) {
        return Course
                .builder()
                .id(courseRequest.id())
                .name(courseRequest.name())
                .description(courseRequest.description())
                .workload(courseRequest.workload())
                .status(courseRequest.status())
                .build();
    }

    public CourseResponse toCourseResponse(Course course) {
        if(course.getCollegeClasses() == null) {
            return CourseResponse.builder()
                    .id(course.getId())
                    .name(course.getName())
                    .description(course.getDescription())
                    .workload(course.getWorkload())
                    .collegeClass(Collections.emptyList())
                    .status(course.getStatus())
                    .createdAt(course.getCreatedAt())
                    .updatedAt(course.getUpdatedAt())
                    .build();
        }

        List<CollegeClassDetailResponse> collegeClassDetails = course.getCollegeClasses()
                .stream()
                .map(collegeClassMapper::toCollegeClassDetailResponse)
                .collect(Collectors.toList());

        return CourseResponse
                .builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .workload(course.getWorkload())
                .collegeClass(collegeClassDetails)
                .status(course.getStatus())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
