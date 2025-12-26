package com.example.demo.service;

import com.example.demo.model.ApartmentUnit;

public interface ApartmentUnitService {
    ApartmentUnit assignUnit(Long userId, ApartmentUnit unit);
    ApartmentUnit getByUser(Long userId);
    ApartmentUnit assignUnitToUser(Long userId, ApartmentUnit unit);

    ApartmentUnit getUnitByUser(Long userId);
}
