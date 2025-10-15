package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.CollegeClassRequest;
import com.StudentManager.StudentManager.DTO.CollegeClassResponse;
import com.StudentManager.StudentManager.DTO.RegistrationDetailResponse;
import com.StudentManager.StudentManager.Model.CollegeClass;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CollegeClassMapper {

    private final RegistrationMapper registrationMapper;

    public CollegeClassMapper(RegistrationMapper registrationMapper) {
        this.registrationMapper = registrationMapper;
    }

    public CollegeClass toCollegeClass(CollegeClassRequest collegeClassRequest) {
        return CollegeClass
                .builder()
                .id(collegeClassRequest.id())
                .period(collegeClassRequest.period())
                .status(collegeClassRequest.status())
                .build();
    }

    public CollegeClassResponse toCollegeClassResponse(CollegeClass collegeClass) {
        if (collegeClass.getRegistrations() == null) {
            return CollegeClassResponse.builder()
                    .id(collegeClass.getId())
                    .period(collegeClass.getPeriod())
                    .status(collegeClass.getStatus())
                    .createdAt(collegeClass.getCreatedAt())
                    .updatedAt(collegeClass.getUpdatedAt())
                    .registrations(Collections.emptyList()) // <-- Define uma lista vazia
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
                .registrations(registrationDetails)
                .status(collegeClass.getStatus())
                .createdAt(collegeClass.getCreatedAt())
                .updatedAt(collegeClass.getUpdatedAt())
                .build();
    }
}
