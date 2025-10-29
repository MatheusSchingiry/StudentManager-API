package com.StudentManager.StudentManager.DTO;

import com.StudentManager.StudentManager.Model.Enum.Status;

import java.util.UUID;

public record UnitRequest(UUID id,
                          String name,
                          String street,
                          Integer number,
                          String city,
                          String state,
                          Integer zipCode,
                          Status status) {
}
