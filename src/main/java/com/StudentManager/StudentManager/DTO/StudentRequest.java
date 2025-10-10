package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.StudentStatus;

import java.time.LocalDate;

public record StudentRequest(String name,
                             Long registerNumber,
                             String address,
                             LocalDate birthDate,
                             String email,
                             Long phoneNumber,
                             StudentStatus status) {
}
