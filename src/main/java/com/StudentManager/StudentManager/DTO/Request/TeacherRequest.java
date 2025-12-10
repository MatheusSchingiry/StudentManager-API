package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Base.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record TeacherRequest(@NotBlank(message = "Name is mandatory.") String name,
                             @NotBlank(message = "Register number is mandatory.") String registerNumber,
                             @NotNull(message = "Date of birth is required.") @Past(message = "The date of birth must be in the past tense.") LocalDate birthDate,
                             @NotNull(message = "Address is mandatory.") @Valid Address address,
                             @NotBlank(message = "e-Mail is mandatory.") @Email(message = "The e-Mail address must be valid.") String email,
                             String phoneNumber,
                             @NotBlank(message = "Specialty is mandatory.") String specialty) {
}
