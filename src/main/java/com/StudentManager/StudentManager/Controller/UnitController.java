package com.StudentManager.StudentManager.Controller;

import com.StudentManager.StudentManager.DTO.UnitRequest;
import com.StudentManager.StudentManager.DTO.UnitResponse;
import com.StudentManager.StudentManager.Model.Unit;
import com.StudentManager.StudentManager.Service.UnitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/units")
public class UnitController {

    private final UnitService unitService;

    public UnitController(UnitService unitService) {
        this.unitService = unitService;
    }

    @GetMapping
    public List<UnitResponse> getAllUnits() {
        return unitService.getAllUnits();
    }

    @GetMapping("/{id}")
    public UnitResponse getUnitById(@PathVariable UUID id) {
        return unitService.getUnitById(id);
    }

    @PostMapping
    public UnitResponse createUnit(@RequestBody UnitRequest unit) {
        return unitService.createUnit(unit);
    }

    @PutMapping("/{id}")
    public UnitResponse editUnitById(@PathVariable UUID id, @RequestBody UnitRequest unit) {
        return unitService.editUnitById(id, unit);
    }

    @DeleteMapping("/{id}")
    public void deleteUnitById(@PathVariable UUID id) {
        unitService.deleteUnitById(id);
    }
}
