/*package com.example.demo.controller;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.service.ApartmentUnitService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/units")
@Tag(name = "ApartmentUnit", description = "Apartment unit management endpoints")
public class ApartmentUnitController {
    
    private final ApartmentUnitService apartmentUnitService;
    
    public ApartmentUnitController(ApartmentUnitService apartmentUnitService) {
        this.apartmentUnitService = apartmentUnitService;
    }
    
    @PostMapping("/assign/{userId}")
    public ResponseEntity<ApartmentUnit> assignUnit(@PathVariable Long userId, @RequestBody ApartmentUnit unit) {
        ApartmentUnit assigned = apartmentUnitService.assignUnitToUser(userId, unit);
        return ResponseEntity.ok(assigned);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<ApartmentUnit> getUnitByUser(@PathVariable Long userId) {
        ApartmentUnit unit = apartmentUnitService.getUnitByUser(userId);
       // return ResponseEntity.ok(unit);
   // }
} */

package com.example.demo.controller;

import com.example.demo.model.ApartmentUnitModel;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/units")
public class ApartmentUnitController {

    private final ApartmentUnitService unitService;

    public ApartmentUnitController(ApartmentUnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping("/assign/{userId}")
    public ResponseEntity<ApartmentUnitModel> assign(
            @PathVariable Long userId,
            @RequestBody ApartmentUnitModel unit) {
        return ResponseEntity.ok(unitService.assignUnitToUser(userId, unit));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApartmentUnitModel> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(unitService.getUnitByUser(userId));
    }
}

