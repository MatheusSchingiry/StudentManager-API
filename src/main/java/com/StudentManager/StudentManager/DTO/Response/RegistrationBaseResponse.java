package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record RegistrationBaseResponse(UUID id,
                                       Integer semester,
                                       LocalDate registrationDate,
                                       String studentName,
                                       CollegeClassRegistrationResponse collegeClass,
                                       Status status,
                                       LocalDateTime createdAt,
                                       LocalDateTime updatedAt) {
}
