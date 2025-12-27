package com.example.demo.repository;

import com.example.demo.model.FacilityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacilityRepository extends JpaRepository<FacilityModel, Long> {
    Optional<FacilityModel> findByName(String name);
}