package com.example.demo.service;

import com.example.demo.model.FacilityModel;

import java.util.List;

public interface FacilityService {
    FacilityModel addFacility(FacilityModel facility);
    List<FacilityModel> getAllFacilities();
}