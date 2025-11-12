package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.DTO.Request.SubjectRequest;
import com.StudentManager.StudentManager.DTO.Response.SubjectBaseResponse;
import com.StudentManager.StudentManager.Mapper.SubjectMapper;
import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Subject;
import com.StudentManager.StudentManager.Repository.SubjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectService(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    public List<SubjectBaseResponse> getAllSubjects() {
        return subjectRepository.findAllByStatus(Status.ACTIVE)
                .stream()
                .map(subjectMapper::toSubjectBaseResponse)
                .collect(Collectors.toList());
    }

    public SubjectBaseResponse getSubjectById(UUID id) {
        return subjectMapper.toSubjectBaseResponse(subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found")));
    }

    @Transactional
    public SubjectBaseResponse createSubject(SubjectRequest subject) {
        Subject subjectEntity = subjectMapper.toSubject(subject);
        subjectEntity.setStatus(Status.ACTIVE);
        return subjectMapper.toSubjectBaseResponse(subjectRepository.save(subjectEntity));
    }

    @Transactional
    public SubjectBaseResponse updateSubject(UUID id, SubjectRequest subjectDetail) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));

        if(subjectDetail.name() != null) { subject.setName(subjectDetail.name());}
        if(subjectDetail.description() != null) { subject.setDescription(subjectDetail.description());}
        if(subjectDetail.creditHours() != null) { subject.setCreditHours(subjectDetail.creditHours());}

        return subjectMapper.toSubjectBaseResponse(subjectRepository.save(subject));
    }

    @Transactional
    public void deleteSubject(UUID id) {
        Subject existingSubject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));

        existingSubject.setStatus(Status.INACTIVE);
        subjectRepository.save(existingSubject);
    }
}
