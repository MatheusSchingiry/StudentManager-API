package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Base.Address;

public record UnitRequest(String name,
                          Address address) {
}
