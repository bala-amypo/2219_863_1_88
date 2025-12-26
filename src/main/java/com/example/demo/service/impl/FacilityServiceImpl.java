package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Facility;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {
    
    private final FacilityRepository facilityRepository;

    @Autowired
    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility addFacility(Facility facility) {
        if (facilityRepository.findByName(facility.getName()).isPresent()) {
            throw new BadRequestException("Facility already exists");
        }
        
        LocalTime openTime = LocalTime.parse(facility.getOpenTime());
        LocalTime closeTime = LocalTime.parse(facility.getCloseTime());
        
        if (openTime.isAfter(closeTime)) {
            throw new BadRequestException("Open time cannot be after close time");
        }
        
        return facilityRepository.save(facility);
    }

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }
}