package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.*;
import com.StudentManager.StudentManager.Model.CollegeClass;
import com.StudentManager.StudentManager.Repository.CourseRepository;
import com.StudentManager.StudentManager.Repository.UnitRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollegeClassMapper {

    private final RegistrationMapper registrationMapper;
    private final CourseMapper courseMapper;
    private final UnitMapper unitMapper;
    private final CourseRepository courseRepository;
    private final UnitRepository unitRepository;

    public CollegeClassMapper(RegistrationMapper registrationMapper, CourseMapper courseMapper, UnitMapper unitMapper, CourseRepository courseRepository, UnitRepository unitRepository) {
        this.registrationMapper = registrationMapper;
        this.courseMapper = courseMapper;
        this.unitMapper = unitMapper;
        this.courseRepository = courseRepository;
        this.unitRepository = unitRepository;
    }

    public CollegeClass toCollegeClass(CollegeClassRequest collegeClassRequest) {
        return CollegeClass
                .builder()
                .id(collegeClassRequest.id())
                .period(collegeClassRequest.period())
                .course(courseRepository.findById(collegeClassRequest.courseId()).orElseThrow(() -> new RuntimeException("Course not found")))
                .unit(unitRepository.findById(collegeClassRequest.unitId()).orElseThrow(() -> new RuntimeException("Unit not found")))
                .status(collegeClassRequest.status())
                .build();
    }

    public CollegeClassResponse toCollegeClassResponse(CollegeClass collegeClass) {
        if (collegeClass.getRegistrations() == null) {
            return CollegeClassResponse.builder()
                    .id(collegeClass.getId())
                    .period(collegeClass.getPeriod())
                    .courses(courseMapper.toCourseResponse(collegeClass.getCourse()))
                    .unit(unitMapper.toUnitResponse(collegeClass.getUnit()))
                    .status(collegeClass.getStatus())
                    .createdAt(collegeClass.getCreatedAt())
                    .updatedAt(collegeClass.getUpdatedAt())
                    .registrations(Collections.emptyList())
                    .build();
        }

        List<RegistrationDetailResponse> registrationDetails = collegeClass.getRegistrations()
                .stream()
                .map(registrationMapper::toRegistrationDetailResponse)
                .collect(Collectors.toList());


        return CollegeClassResponse
                .builder()
                .id(collegeClass.getId())
                .period(collegeClass.getPeriod())
                .courses(courseMapper.toCourseResponse(collegeClass.getCourse()))
                .unit(unitMapper.toUnitResponse(collegeClass.getUnit()))
                .registrations(registrationDetails)
                .status(collegeClass.getStatus())
                .createdAt(collegeClass.getCreatedAt())
                .updatedAt(collegeClass.getUpdatedAt())
                .build();
    }

    public CollegeClassDetailResponse toCollegeClassDetailResponse(CollegeClass collegeClass) {
        return CollegeClassDetailResponse.builder()
                .id(collegeClass.getId())
                .status(collegeClass.getStatus())
                .period(collegeClass.getPeriod())
                .build();
    }
}
