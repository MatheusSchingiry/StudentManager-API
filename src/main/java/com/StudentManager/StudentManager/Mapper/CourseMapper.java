package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.*;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Registration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CourseMapper {

    private final CollegeClassMapper collegeClassMapper;
    private final UnitMapper unitMapper;

    public CourseMapper(@Lazy CollegeClassMapper collegeClassMapper, @Lazy UnitMapper unitMapper) {
        this.collegeClassMapper = collegeClassMapper;
        this.unitMapper = unitMapper;
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

        Set<UnitResponse> unitDetails = course.getUnits()
                .stream()
                .map(unitMapper::toUnitResponse)
                .collect(Collectors.toSet());

        return CourseResponse
                .builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .workload(course.getWorkload())
                .collegeClass(collegeClassDetails)
                .units(unitDetails)
                .status(course.getStatus())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }

    public CourseDetailResponse toCourseDetailResponse(Course course) {
        return CourseDetailResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .workload(course.getWorkload())
                .build();
    }
}
