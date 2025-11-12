package com.StudentManager.StudentManager.DTO.Request;

import java.util.UUID;

public record RegistrationRequest(Integer semester,
                                  UUID studentId,
                                  UUID collegeClassId) {
}
