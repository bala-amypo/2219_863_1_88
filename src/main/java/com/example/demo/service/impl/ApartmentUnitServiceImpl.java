package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.ApartmentUnit;
import com.example.demo.model.User;
import com.example.demo.repository.ApartmentUnitRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ApartmentUnitService;
import org.springframework.stereotype.Service;

@Service
public class ApartmentUnitServiceImpl implements ApartmentUnitService {
    
    private final ApartmentUnitRepository apartmentUnitRepository;
    private final UserRepository userRepository;
    
    public ApartmentUnitServiceImpl(ApartmentUnitRepository apartmentUnitRepository, UserRepository userRepository) {
        this.apartmentUnitRepository = apartmentUnitRepository;
        this.userRepository = userRepository;
    }
    
    @Override
    public ApartmentUnit assignUnitToUser(Long userId, ApartmentUnit unit) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (apartmentUnitRepository.existsByUnitNumber(unit.getUnitNumber())) {
            throw new BadRequestException("Unit number constraint violation");
        }
        
        if (unit.getFloor() < 0) {
            throw new BadRequestException("Floor must be >= 0");
        }
        
        unit.setOwner(user);
        return apartmentUnitRepository.save(unit);
    }
    
    @Override
    public ApartmentUnit getUnitByUser(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return apartmentUnitRepository.findByOwner(user)
            .orElseThrow(() -> new ResourceNotFoundException("Unit not found"));
    }
}