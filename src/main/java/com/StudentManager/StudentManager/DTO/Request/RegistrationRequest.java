package com.StudentManager.StudentManager.DTO.Request;


import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegistrationRequest(Integer semester,
                                  @NotNull(message = "Student is required.") UUID studentId,
                                  @NotNull(message = "College Class is required.") UUID collegeClassId) {
}
