package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Enum.Period;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CollegeClassRequest(@NotNull(message = "Period is required.") Period period,
                                  @NotNull(message = "Unit is required.") UUID unitId,
                                  @NotNull(message = "Course is required.") UUID course) {
}
