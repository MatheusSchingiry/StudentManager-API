package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.RegistrationResponse;
import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<RegistrationResponse> getAll() {
        return registrationService.getAllRegistration();
    }

    @GetMapping("/{id}")
    public RegistrationResponse getRegistrationById(@PathVariable UUID id) {
        return registrationService.getRegistrationById(id);
    }

    @GetMapping("/student/{studentId}")
    public RegistrationResponse getRegistrationByStudentId(@PathVariable UUID studentId) {
        return registrationService.getRegistrationByStudentId(studentId);
    }

    @PostMapping
    public RegistrationResponse createRegistration(@RequestBody RegistrationRequest registration) {
        return registrationService.createRegistration(registration);
    }

    @PutMapping("/{id}")
    public RegistrationResponse editRegistrationById(@PathVariable UUID id, @RequestBody RegistrationRequest registration) {
        return registrationService.editRegistrationById(id, registration);
    }

    @DeleteMapping("/{id}")
    public void deleteRegistrationById(@PathVariable UUID id) {
        registrationService.deleteRegistrationById(id);
    }
}
