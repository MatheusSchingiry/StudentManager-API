package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record CourseResponse(UUID id,
                             String name,
                             String description,
                             Integer workload,
                             List<CollegeClassDetailResponse> collegeClass,
                             Status status,
                             LocalDateTime createdAt,
                             LocalDateTime updatedAt) {
}
