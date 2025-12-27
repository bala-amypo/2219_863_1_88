/*package com.example.demo.controller;

import com.example.demo.model.Facility;
import com.example.demo.service.FacilityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/facilities")
@Tag(name = "Facility", description = "Facility management endpoints")
public class FacilityController {
    
    private final FacilityService facilityService;
    
    public FacilityController(FacilityService facilityService) {
        this.facilityService = facilityService;
    }
    
    @PostMapping("/")
    public ResponseEntity<Facility> addFacility(@RequestBody Facility facility) {
        Facility added = facilityService.addFacility(facility);
        return ResponseEntity.ok(added);
    }
    
    @GetMapping("/")
    public ResponseEntity<List<Facility>> getAllFacilities() {
        List<Facility> facilities = facilityService.getAllFacilities();
        return ResponseEntity.ok(facilities);
    }
}*/

package com.example.demo.controller;

import com.example.demo.model.FacilityModel;
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
    public ResponseEntity<FacilityModel> add(@RequestBody FacilityModel facility) {
        return ResponseEntity.ok(facilityService.addFacility(facility));
    }

    @GetMapping
    public ResponseEntity<List<FacilityModel>> all() {
        return ResponseEntity.ok(facilityService.getAllFacilities());
    }
}