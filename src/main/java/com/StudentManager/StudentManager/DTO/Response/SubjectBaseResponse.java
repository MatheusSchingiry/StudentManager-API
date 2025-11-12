package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record SubjectBaseResponse(UUID id,
                                  String name,
                                  String description,
                                  Integer creditHours,
                                  Status status,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
}
