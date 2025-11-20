package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Base.Address;
import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record TeacherBaseResponse(UUID id,
                                  String name,
                                  String registerNumber,
                                  LocalDate birthDate,
                                  Address address,
                                  String email,
                                  String phoneNumber,
                                  String specialty,
                                  LocalDate hireDate,
                                  List<String> unitsNames,
                                  List<String> subjectsNames,
                                  Status status,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
}
