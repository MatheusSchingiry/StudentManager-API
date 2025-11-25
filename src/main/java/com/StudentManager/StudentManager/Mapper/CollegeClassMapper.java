package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.Response.CollegeClassBaseResponse;
import com.StudentManager.StudentManager.DTO.Response.CollegeClassRegistrationResponse;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Model.Course;
import com.StudentManager.StudentManager.Model.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CollegeClassMapper {

    public CollegeClass toCollegeClass(CollegeClassRequest collegeClassRequest, Unit unit, Course course) {
        return CollegeClass.builder()
                .period(collegeClassRequest.period())
                .unit(unit)
                .course(course)
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

    public CollegeClassRegistrationResponse toCollegeClassRegistrationResponse(CollegeClass collegeClass) {
        return CollegeClassRegistrationResponse.builder()
                .period(collegeClass.getPeriod().name())
                .unitName(collegeClass.getUnit().getName())
                .courseName(collegeClass.getCourse().getName())
                .build();
    }
}
