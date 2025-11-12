package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.CourseRequest;
import com.StudentManager.StudentManager.DTO.Response.CourseBaseResponse;
import com.StudentManager.StudentManager.Model.Course;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CourseMapper {

    public Course toCourse(CourseRequest courseRequest) {
        return Course.builder()
                .name(courseRequest.name())
                .description(courseRequest.description())
                .workload(courseRequest.workload())
                .build();
    }

    public CourseBaseResponse toCourseBaseResponse(Course course) {
        return CourseBaseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .workload(course.getWorkload())
                .periods(course.getPeriods().stream().map(Enum::name).collect(java.util.stream.Collectors.toSet()))
                .unitsNames(course.getUnits().stream().map(unit -> unit.getName()).collect(java.util.stream.Collectors.toSet()))
                .status(course.getStatus())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
