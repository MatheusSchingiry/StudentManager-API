package com.StudentManager.StudentManager.DTO.Response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CollegeClassBaseResponse(UUID id,
                                       String period,
                                       String unitName,
                                       String courseName) {
}
