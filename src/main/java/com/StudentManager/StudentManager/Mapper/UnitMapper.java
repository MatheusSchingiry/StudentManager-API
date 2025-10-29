package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.*;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Unit;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UnitMapper {

    private final CourseMapper courseMapper;

    public UnitMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public Unit toUnit(UnitRequest unitRequest) {
        return Unit
                .builder()
                .id(unitRequest.id())
                .name(unitRequest.name())
                .street(unitRequest.street())
                .number(unitRequest.number())
                .city(unitRequest.city())
                .state(unitRequest.state())
                .zipCode(unitRequest.zipCode())
                .status(unitRequest.status())
                .build();
    }

    public UnitResponse toUnitResponse(Unit unit) {

        if (unit.getCourses() == null) {
            return UnitResponse.builder()
                    .id(unit.getId())
                    .name(unit.getName())
                    .street(unit.getStreet())
                    .number(unit.getNumber())
                    .city(unit.getCity())
                    .state(unit.getState())
                    .zipCode(unit.getZipCode())
                    .status(unit.getStatus())
                    .createdAt(unit.getCreatedAt())
                    .updatedAt(unit.getUpdatedAt())
                    .build();
        }

        Set<CourseDetailResponse> courseDetail = unit.getCourses()
                .stream()
                .map(courseMapper::toCourseDetailResponse)
                .collect(Collectors.toSet());

        return UnitResponse
                .builder()
                .id(unit.getId())
                .name(unit.getName())
                .street(unit.getStreet())
                .number(unit.getNumber())
                .city(unit.getCity())
                .state(unit.getState())
                .zipCode(unit.getZipCode())
                .status(unit.getStatus())
                .createdAt(unit.getCreatedAt())
                .updatedAt(unit.getUpdatedAt())
                .build();
    }

    public UnitDetailResponse toUnitDetailResponse(Unit unit) {
        return UnitDetailResponse.builder()
                .id(unit.getId())
                .name(unit.getName())
                .street(unit.getStreet())
                .number(unit.getNumber())
                .city (unit.getCity())
                .state(unit.getState())
                .zipCode(unit.getZipCode())
                .build();
    }
}
