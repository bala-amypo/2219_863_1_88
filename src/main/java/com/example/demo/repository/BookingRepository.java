package com.example.demo.repository;

import com.example.demo.model.BookingModel;
import com.example.demo.model.FacilityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<BookingModel, Long> {
    List<BookingModel> findByFacilityAndStartTimeLessThanAndEndTimeGreaterThan(
            FacilityModel facility,
            LocalDateTime endTime,
            LocalDateTime startTime
    );
}