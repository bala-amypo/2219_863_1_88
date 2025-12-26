package com.example.demo.repository;

import com.example.demo.model.ApartmentUnit;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApartmentUnitRepository extends JpaRepository<ApartmentUnit, Long> {
    Optional<ApartmentUnit> findByOwner(User owner);
}
