package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.Facility;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.service.FacilityService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public Facility addFacility(Facility facility) {
        Optional<Facility> existing = facilityRepository.findByName(facility.getName());
        if (existing.isPresent()) {
            throw new BadRequestException("Facility name already exists (duplicate)");
        }
        if (facility.getOpenTime().compareTo(facility.getCloseTime()) >= 0) {
            throw new BadRequestException("Invalid open/close times (time)");
        }
        return facilityRepository.save(facility);
    }

    @Override
    public List<Facility> getAllFacilities() {
        return facilityRepository.findAll();
    }
}
