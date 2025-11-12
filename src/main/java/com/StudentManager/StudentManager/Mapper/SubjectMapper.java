package com.StudentManager.StudentManager.Mapper;

import com.StudentManager.StudentManager.DTO.Request.SubjectRequest;
import com.StudentManager.StudentManager.DTO.Response.SubjectBaseResponse;
import com.StudentManager.StudentManager.Model.Subject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectMapper {

    public Subject toSubject(SubjectRequest subjectRequest) {
        return Subject.builder()
                .name(subjectRequest.name())
                .description(subjectRequest.description())
                .creditHours(subjectRequest.creditHours())
                .build();
    }

    public SubjectBaseResponse toSubjectBaseResponse(Subject subject) {
        return SubjectBaseResponse.builder()
                .id(subject.getId())
                .name(subject.getName())
                .description(subject.getDescription())
                .creditHours(subject.getCreditHours())
                .status(subject.getStatus())
                .createdAt(subject.getCreatedAt())
                .updatedAt(subject.getUpdatedAt())
                .build();
    }
}
