

package com.example.demo.controller;

import com.example.demo.model.Facility;
import com.example.demo.service.FacilityService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/facilities")
public class FacilityController {

    private final FacilityService facilityService;

    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }

    @PostMapping
    public ResponseEntity<Facility> add(@RequestBody Facility facility) {
        return ResponseEntity.ok(facilityService.addFacility(facility));
    }

    @GetMapping
    public ResponseEntity<List<Facility>> all() {
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }
}