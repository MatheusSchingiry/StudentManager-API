package com.StudentManager.StudentManager.DTO.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SubjectRequest(@NotBlank(message = "Name is mandatory.") String name,
                             String description,
                             @NotNull(message = "Credit hours is required.") Integer creditHours) {
}
