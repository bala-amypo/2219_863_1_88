package com.example.demo.service;

import com.example.demo.model.ApartmentUnitModel;

public interface ApartmentUnitService {
    ApartmentUnitModel assignUnitToUser(Long userId, ApartmentUnitModel unit);
    ApartmentUnitModel getUnitByUser(Long userId);
}