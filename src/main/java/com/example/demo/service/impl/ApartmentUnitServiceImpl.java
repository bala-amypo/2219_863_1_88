package com.example.demo.service.impl;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.repository.ApartmentUnitRepository;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentUnitServiceImpl implements ApartmentUnitService {

    private final ApartmentUnitRepository apartmentUnitRepository;

    public ApartmentUnitServiceImpl(ApartmentUnitRepository apartmentUnitRepository) {
        this.apartmentUnitRepository = apartmentUnitRepository;
    }

    @Override
    public List<ApartmentUnit> getByUser(Long userId) {
        return apartmentUnitRepository.findByUserId(userId);
    }
}
