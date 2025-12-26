package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.stereotype.Service;

@Service
public class ApartmentUnitServiceImpl implements ApartmentUnitService {

    private final ApartmentUnitRepository apartmentUnitRepository;
    private final UserRepository userRepository;

    public ApartmentUnitServiceImpl(ApartmentUnitRepository apartmentUnitRepository,
                                    UserRepository userRepository) {
        this.apartmentUnitRepository = apartmentUnitRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApartmentUnit assignUnit(Long userId, ApartmentUnit unit) {
        return assignUnitToUser(userId, unit);
    }

    @Override
    public ApartmentUnit getByUser(Long userId) {
        return getUnitByUser(userId);
    }

    @Override
    public ApartmentUnit assignUnitToUser(Long userId, ApartmentUnit unit) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        unit.setOwner(user);
        ApartmentUnit saved = apartmentUnitRepository.save(unit);
        user.setApartmentUnit(saved);
        return saved;
    }

    @Override
    public ApartmentUnit getUnitByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return apartmentUnitRepository.findByOwner(user)
                .orElseThrow(() -> new RuntimeException("Unit not found"));
    }
}
