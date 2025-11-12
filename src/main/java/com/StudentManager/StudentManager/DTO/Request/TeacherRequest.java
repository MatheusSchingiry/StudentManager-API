package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Base.Address;

import java.time.LocalDate;

public record TeacherRequest(String name,
                             String registerNumber,
                             LocalDate birthDate,
                             Address address,
                             String email,
                             String phoneNumber,
                             String specialty) {
}
