package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Base.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public record EditTeacherRequest(String name,
                                 String registerNumber,
                                 @Past(message = "The date of birth must be in the past tense.") LocalDate birthDate,
                                 @Valid Address address,
                                 @Email(message = "The e-Mail address must be valid.") String email,
                                 String phoneNumber,
                                 String specialty) {
}
