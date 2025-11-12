package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Base.Address;

import java.time.LocalDate;

public record StudentRequest(String name,
                             String registerNumber,
                             LocalDate birthDate,
                             Address address,
                             String email,
                             String phoneNumber) {
}
