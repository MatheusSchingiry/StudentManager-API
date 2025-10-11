package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record StudentResponse(UUID id,
                              String name,
                              Long registerNumber,
                              String address,
                              LocalDate birthDate,
                              String email,
                              Long phoneNumber,
                              Status status,
                              OffsetDateTime createdAt,
                              OffsetDateTime updatedAt) {
}
