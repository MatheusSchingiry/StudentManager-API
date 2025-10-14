package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record RegistrationResponse(UUID id,
                                   StudentResponse student,
                                   Status status,
                                   LocalDate registrationDate,
                                   Integer semester,
                                   LocalDateTime createdAt,
                                   LocalDateTime updatedAt) {
}
