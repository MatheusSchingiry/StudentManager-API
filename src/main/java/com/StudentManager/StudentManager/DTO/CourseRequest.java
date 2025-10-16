package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;

import java.util.UUID;

public record CourseRequest(UUID id,
                            String name,
                            String description,
                            Integer workload,
                            Status status) {
}
