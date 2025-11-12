package com.StudentManager.StudentManager.DTO.Request;

import com.StudentManager.StudentManager.Model.Enum.Period;

import java.util.UUID;

public record CollegeClassRequest(Period period,
                                  UUID unitId,
                                  UUID course) {
}
