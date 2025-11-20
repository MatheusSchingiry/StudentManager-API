package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Enum.Period;
import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Builder
public record CourseBaseResponse(UUID id,
                                 String name,
                                 String description,
                                 Integer workload,
                                 Set<Period> periods,
                                 List<String> unitsNames,
                                 Status status,
                                 LocalDateTime createdAt,
                                 LocalDateTime updatedAt) {
}
