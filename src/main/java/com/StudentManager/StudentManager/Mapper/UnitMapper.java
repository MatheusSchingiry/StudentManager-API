package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.UnitRequest;
import com.StudentManager.StudentManager.DTO.Response.UnitBaseResponse;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UnitMapper {

    private final UnitRepository unitRepository;

    public Unit toUnit(UnitRequest unitRequest) {
        return Unit.builder()
                .name(unitRequest.name())
                .address(unitRequest.address())
                .build();
    }

    public UnitBaseResponse toUnitBaseResponse(Unit unit) {
        List<Unit> coursesNames = unitRepository.findCoursesIdByUnitId(unit.getId());

        List<String> courseNamesList = coursesNames.stream()
                .flatMap(c -> c.getCourses().stream())
                .map(course -> course.getName())
                .distinct()
                .collect(Collectors.toList());

        return UnitBaseResponse.builder()
                .id(unit.getId())
                .name(unit.getName())
                .address(unit.getAddress())
                .coursesNames(courseNamesList)
                .status(unit.getStatus())
                .createdAt(unit.getCreatedAt())
                .updatedAt(unit.getUpdatedAt())
                .build();
    }
}
