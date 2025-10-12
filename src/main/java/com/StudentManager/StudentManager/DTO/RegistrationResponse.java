package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Student;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.UUID;

@Builder
public record RegistrationResponse(UUID id,
                                   Student student,
                                   Status status,
                                   LocalDate registrationDate,
                                   Integer semester,
                                   LocalDateTime createdAt,
                                   LocalDateTime updatedAt) {
}
