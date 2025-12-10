package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Base.Address;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UnitRequest(@NotBlank(message = "Name is mandatory.") String name,
                          @NotNull(message = "Address is mandatory.") @Valid Address address) {
}
