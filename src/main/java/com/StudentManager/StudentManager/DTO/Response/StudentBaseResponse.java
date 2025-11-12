package com.StudentManager.StudentManager.DTO.Response;

import com.StudentManager.StudentManager.Model.Base.Address;
import com.StudentManager.StudentManager.Model.Enum.Status;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record StudentBaseResponse(UUID id,
                                  String name,
                                  String registerNumber,
                                  String birthDate,
                                  Address address,
                                  String email,
                                  String phoneNumber,
                                  Status status,
                                  LocalDateTime createdAt,
                                  LocalDateTime updatedAt) {
}
