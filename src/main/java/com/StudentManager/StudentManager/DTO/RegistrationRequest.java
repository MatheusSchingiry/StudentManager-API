package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;

import java.time.LocalDate;
import java.util.UUID;

public record RegistrationRequest(UUID studentId,
                                  Status status,
                                  LocalDate registrationDate,
                                  Integer semester) {
}
