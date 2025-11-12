package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.Response.CollegeClassBaseResponse;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollegeClassMapper {

    private final UnitRepository unitRepository;
    private final CourseRepository courseRepository;

    public CollegeClass toCollegeClass(CollegeClassRequest collegeClassRequest) {
        return CollegeClass.builder()
                .period(collegeClassRequest.period())
                .unit(unitRepository.findById(collegeClassRequest.unitId()).orElseThrow(() -> new RuntimeException("Unit not found")))
                .course(courseRepository.findById(collegeClassRequest.course()).orElseThrow(() -> new RuntimeException("Course not found")))
                .build();
    }

    public CollegeClassBaseResponse toCollegeClassBaseResponse(CollegeClass collegeClass) {
        return CollegeClassBaseResponse.builder()
                .id(collegeClass.getId())
                .period(collegeClass.getPeriod().name())
                .unitName(collegeClass.getUnit().getName())
                .courseName(collegeClass.getCourse().getName())
                .status(collegeClass.getStatus())
                .createdAt(collegeClass.getCreatedAt())
                .updatedAt(collegeClass.getUpdatedAt())
                .build();
    }
}
