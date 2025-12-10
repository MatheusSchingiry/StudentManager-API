package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Enum.Period;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CourseRequest(@NotBlank(message = "Name is mandatory.") String name,
                            String description,
                            @NotNull(message = "Workload is required.") Integer workload,
                            Set<Period> periods,
                            List<UUID> unitId) {
}
