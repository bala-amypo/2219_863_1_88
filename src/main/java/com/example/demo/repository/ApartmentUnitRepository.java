package com.example.demo.repository;

import com.example.demo.model.ApartmentUnitModel;
import com.example.demo.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartmentUnitRepository extends JpaRepository<ApartmentUnitModel, Long> {
    boolean existsByUnitNumber(String unitNumber);
    Optional<ApartmentUnitModel> findByOwner(UserModel owner);
}