package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.FacilityModel;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.service.FacilityService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacilityServiceImpl implements FacilityService {

    private final FacilityRepository facilityRepository;

    public FacilityServiceImpl(FacilityRepository facilityRepository) {
        this.facilityRepository = facilityRepository;
    }

    @Override
    public FacilityModel addFacility(FacilityModel facility) {
        if (facility.getOpenTime().compareTo(facility.getCloseTime()) >= 0) {
            throw new BadRequestException("Invalid time");
        }
        return facilityRepository.save(facility);
    }

    @Override
    public List<FacilityModel> getAllFacilities() {
        return facilityRepository.findAll();
    }
}