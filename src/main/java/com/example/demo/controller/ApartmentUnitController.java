package com.example.demo.controller;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/units")
public class ApartmentUnitController {

    @Autowired
    private ApartmentUnitService unitService;

    @PostMapping("/assign/{userId}")
    public ApartmentUnit assignUnit(@PathVariable Long userId, @RequestBody ApartmentUnit unit) {
        return unitService.assignUnit(userId, unit);
    }

    @GetMapping("/user/{userId}")
    public ApartmentUnit getUnit(@PathVariable Long userId) {
        return unitService.getUnitByUser(userId);
    }
}
