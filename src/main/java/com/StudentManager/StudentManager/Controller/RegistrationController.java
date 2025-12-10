package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.Request.RegistrationRequest;
import com.StudentManager.StudentManager.DTO.Response.RegistrationBaseResponse;
import com.StudentManager.StudentManager.Service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) { this.registrationService = registrationService; }

    @GetMapping
    public ResponseEntity<List<RegistrationBaseResponse>> getAllRegistrations() {
        return ResponseEntity.ok(registrationService.getAllRegistrations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationBaseResponse> getRegistrationById(@PathVariable UUID id) {
        return ResponseEntity.ok(registrationService.getRegistrationById(id));
    }

    @PostMapping
    public ResponseEntity<String> createRegistration(@RequestBody @Valid RegistrationRequest registration) {
        registrationService.createRegistration(registration);
        return ResponseEntity.status(201).body("Registration created successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegistration(@PathVariable UUID id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.status(200).body("Registration deleted successfully");
    }
}
