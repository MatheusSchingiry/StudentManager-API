package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record RegistrationDetailResponse(UUID id,
                                         StudentResponse student,
                                         Status status,
                                         LocalDate registrationDate,
                                         Integer semester) {
}
