package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Period;
import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record CollegeClassDetailResponse(UUID id,
                                         Period period,
                                         Status status) {
}
