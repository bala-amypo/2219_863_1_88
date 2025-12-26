package com.example.demo.repository;

import com.example.demo.model.ApartmentUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentUnitRepository extends JpaRepository<ApartmentUnit, Long> {

    List<ApartmentUnit> findByUserId(Long userId);
}
