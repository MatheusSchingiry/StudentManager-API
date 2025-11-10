package com.StudentManager.StudentManager.Service;

import com.StudentManager.StudentManager.Model.Enum.Status;
import com.StudentManager.StudentManager.Model.Teacher;
import com.StudentManager.StudentManager.Repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) { this.teacherRepository = teacherRepository; }

    public List<Teacher> getAllTeachers() { return teacherRepository.findAll();}

    public Teacher getTeacherById(UUID id) { return teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));}

    public Teacher createTeacher(Teacher teacher) {
        teacher.setStatus(Status.ACTIVE);
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(UUID id, Teacher teacherDetail) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        if(teacherDetail.getName() != null) { teacher.setName(teacherDetail.getName());}
        if(teacherDetail.getRegisterNumber() != null) { teacher.setRegisterNumber(teacherDetail.getRegisterNumber());}
        if(teacherDetail.getBirthDate() != null) { teacher.setBirthDate(teacherDetail.getBirthDate());}
        if(teacherDetail.getEmail() != null) { teacher.setEmail(teacherDetail.getEmail());}
        if(teacherDetail.getPhoneNumber() != null) { teacher.setPhoneNumber(teacherDetail.getPhoneNumber());}
        if(teacherDetail.getSpecialty() != null) { teacher.setSpecialty(teacherDetail.getSpecialty());}

        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(UUID id) {
        Teacher existingTeacher = teacherRepository.findById(id).orElseThrow(() -> new RuntimeException("Teacher not found"));

        existingTeacher.setStatus(Status.INACTIVE);
        teacherRepository.save(existingTeacher);
    }
}
