package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Enum.Period;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public record CourseRequest(String name,
                            String description,
                            Integer workload,
                            Set<Period> periods,
                            List<UUID> unitId) {
}
