package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) { this.unitService = unitService; }

    @GetMapping
    public List<Unit> getAllUnits() { return unitService.getAllUnits(); }

    @GetMapping("/{id}")
    public Unit getUnitById(@PathVariable UUID id) { return unitService.getUnitById(id); }

    @PostMapping
    public Unit createUnit(@RequestBody Unit unit) { return unitService.createUnit(unit); }

    @PutMapping("/{id}")
    public Unit updateUnit(@PathVariable UUID id, @RequestBody Unit unit) { return unitService.updateUnit(id, unit); }

    @DeleteMapping("/{id}")
    public void deleteUnit(@PathVariable UUID id) { unitService.deleteUnit(id); }
}
