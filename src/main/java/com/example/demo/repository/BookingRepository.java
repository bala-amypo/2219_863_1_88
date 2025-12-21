package com.example.demo.repository;

import com.example.demo.model.Booking;
import com.example.demo.model.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.LocalDateTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Long>{
    List<Booking>findByFacilityAndStartTimeLessThanEndTimeGreaterThan(Facility facility,LocalDateTime start,LocalDateTime end);
}
