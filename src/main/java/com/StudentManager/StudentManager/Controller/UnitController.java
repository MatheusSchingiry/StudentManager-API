package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Service.UnitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) { this.unitService = unitService; }

    @GetMapping
    public ResponseEntity<List<Unit>> getAllUnits() {
        return ResponseEntity.ok(unitService.getAllUnits());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable UUID id) {
        return ResponseEntity.ok(unitService.getUnitById(id));
    }

    @PostMapping
    public ResponseEntity<String> createUnit(@RequestBody Unit unit) {
        unitService.createUnit(unit);
        return ResponseEntity.status(201).body("Unit created successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUnit(@PathVariable UUID id, @RequestBody Unit unit) {
        unitService.updateUnit(id, unit);
        return ResponseEntity.status(200).body("Unit updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUnit(@PathVariable UUID id) {
        unitService.deleteUnit(id);
        return ResponseEntity.status(200).body("Unit deleted successfully");
    }
}
