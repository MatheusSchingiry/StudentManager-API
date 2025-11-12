package com.StudentManager.StudentManager.DTO.Response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record RegistrationBaseResponse(UUID id,
                                       Integer semester,
                                       LocalDate registrationDate,
                                       String studentName,
                                       CollegeClassRegistrationResponse collegeClass) {
}
