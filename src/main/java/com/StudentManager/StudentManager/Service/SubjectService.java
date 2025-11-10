package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Subject;
import com.StudentManager.StudentManager.Repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) { this.subjectRepository = subjectRepository;}

    public List<Subject> getAllSubjects() { return subjectRepository.findAll();}

    public Subject getSubjectById(UUID id) { return subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));}

    public Subject createSubject(Subject subject) {
        subject.setStatus(Status.ACTIVE);
        return subjectRepository.save(subject);
    }

    public Subject updateSubject(UUID id, Subject subjectDetail) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));

        if(subjectDetail.getName() != null) { subject.setName(subjectDetail.getName());}
        if(subjectDetail.getDescription() != null) { subject.setDescription(subjectDetail.getDescription());}
        if(subjectDetail.getCreditHours() != null) { subject.setCreditHours(subjectDetail.getCreditHours());}

        return subjectRepository.save(subject);
    }

    public void deleteSubject(UUID id) {
        Subject existingSubject = subjectRepository.findById(id).orElseThrow(() -> new RuntimeException("Subject not found"));

        existingSubject.setStatus(Status.INACTIVE);
        subjectRepository.save(existingSubject);
    }
}
