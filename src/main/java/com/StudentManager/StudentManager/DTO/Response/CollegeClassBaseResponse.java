package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CollegeClassBaseResponse(UUID id,
                                       String period,
                                       String unitName,
                                       String courseName,
                                       Status status,
                                       LocalDateTime createdAt,
                                       LocalDateTime updatedAt) {
}
