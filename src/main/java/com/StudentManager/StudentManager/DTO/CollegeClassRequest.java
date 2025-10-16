package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Period;
import com.StudentManager.StudentManager.Model.Enum.Status;

import java.util.UUID;

public record CollegeClassRequest(UUID id,
                                  Period period,
                                  Status status,
                                  UUID courseId) {
}
