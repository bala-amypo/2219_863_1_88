package com.example.demo.controller;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/units")
public class ApartmentUnitController {

    private final ApartmentUnitService service;

    public ApartmentUnitController(ApartmentUnitService service) {
        this.service = service;
    }

    @PostMapping("/assign/{userId}")
    public ApartmentUnit assign(
            @PathVariable Long userId,
            @RequestBody ApartmentUnit unit
    ) {
        return service.assignUnit(userId, unit);
    }

    @GetMapping("/user/{userId}")
    public ApartmentUnit get(@PathVariable Long userId) {
        return service.getByUser(userId);
    }
}
