package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Registration;
import com.StudentManager.StudentManager.Service.RegistrationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) { this.registrationService = registrationService; }

    @GetMapping
    public List<Registration> getAllRegistrations() { return registrationService.getAllRegistrations(); }

    @GetMapping("/{id}")
    public Registration getRegistrationById(@PathVariable UUID id) { return registrationService.getRegistrationById(id); }

    @PostMapping
    public Registration createRegistration(@RequestBody Registration registration) { return registrationService.createRegistration(registration); }

    @DeleteMapping("/{id}")
    public void deleteRegistration(@PathVariable UUID id) {
        registrationService.deleteRegistration(id);
    }
}
