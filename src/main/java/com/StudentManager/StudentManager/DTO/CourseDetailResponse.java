package com.StudentManager.StudentManager.DTO;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CourseDetailResponse(UUID id,
                                   String name,
                                   String description,
                                   Integer workload) {
}
