package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Base.Address;
import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Builder
public record UnitBaseResponse(UUID id,
                               String name,
                               Address address,
                               Set<String> coursesNames,
                               Status status,
                               LocalDateTime createdAt,
                               LocalDateTime updatedAt) {
}
