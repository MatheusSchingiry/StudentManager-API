package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record UnitResponse(UUID id,
                           String name,
                           String street,
                           Integer number,
                           String city,
                           String state,
                           Integer zipCode,
                           Status status,
                           LocalDateTime createdAt,
                           LocalDateTime updatedAt) {
}
