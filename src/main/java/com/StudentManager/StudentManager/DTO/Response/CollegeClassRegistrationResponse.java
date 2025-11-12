package com.StudentManager.StudentManager.DTO.Response;

import lombok.Builder;

@Builder
public record CollegeClassRegistrationResponse(String period,
                                               String unitName,
                                               String courseName) {
}
