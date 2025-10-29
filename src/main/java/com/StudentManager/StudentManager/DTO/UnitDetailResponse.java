package com.StudentManager.StudentManager.DTO;

import lombok.Builder;

import java.util.UUID;

@Builder
public record UnitDetailResponse(UUID id,
                                 String name,
                                 String street,
                                 Integer number,
                                 String city,
                                 String state,
                                 Integer zipCode) {
}
