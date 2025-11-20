package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.CourseRequest;
import com.StudentManager.StudentManager.DTO.Response.CourseBaseResponse;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class CourseMapper {

    private final UnitRepository unitRepository;
    private final CourseRepository courseRepository;

    public Course toCourse(CourseRequest courseRequest) {
        List<Unit> units = new ArrayList<>();

        for(var id : courseRequest.unitId()) {
            Unit unit = unitRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Unit not found with id: " + id));
            units.add(unit);
        }

        return Course.builder()
                .name(courseRequest.name())
                .description(courseRequest.description())
                .workload(courseRequest.workload())
                .periods(courseRequest.periods())
                .units(units)
                .build();
    }

    public CourseBaseResponse toCourseBaseResponse(Course course) {
        List<Course> unitsId = courseRepository.findUnitsIdByCourseId(course.getId());

        List<String> unitsNames = unitsId.stream()
                .flatMap(c -> c.getUnits().stream())
                .map(Unit::getName)
                .distinct()
                .collect(Collectors.toList());

        return CourseBaseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .workload(course.getWorkload())
                .periods(course.getPeriods())
                .unitsNames(unitsNames)
                .status(course.getStatus())
                .createdAt(course.getCreatedAt())
                .updatedAt(course.getUpdatedAt())
                .build();
    }
}
