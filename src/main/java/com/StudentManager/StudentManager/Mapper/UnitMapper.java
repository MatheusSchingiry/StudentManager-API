package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.UnitRequest;
import com.StudentManager.StudentManager.DTO.Response.UnitBaseResponse;
import com.StudentManager.StudentManager.Model.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UnitMapper {

    public Unit toUnit(UnitRequest unitRequest) {
        return Unit.builder()
                .name(unitRequest.name())
                .address(unitRequest.address())
                .build();
    }

    public UnitBaseResponse toUnitBaseResponse(Unit unit) {
        return UnitBaseResponse.builder()
                .id(unit.getId())
                .name(unit.getName())
                .address(unit.getAddress())
                .coursesNames(unit.getCourses().stream().map(course -> course.getName()).collect(Collectors.toSet()))
                .status(unit.getStatus())
                .createdAt(unit.getCreatedAt())
                .updatedAt(unit.getUpdatedAt())
                .build();
    }
}
