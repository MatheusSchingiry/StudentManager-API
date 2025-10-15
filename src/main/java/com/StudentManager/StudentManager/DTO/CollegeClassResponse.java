package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Period;
import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record CollegeClassResponse(UUID id,
                                   Period period,
                                   List<RegistrationDetailResponse> registrations,
                                   Status status,
                                   LocalDateTime createdAt,
                                   LocalDateTime updatedAt) {
}
