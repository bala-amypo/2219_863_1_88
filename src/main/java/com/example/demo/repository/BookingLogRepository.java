package com.example.demo.repository;

import com.example.demo.model.BookingLogModel;
import com.example.demo.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingLogRepository extends JpaRepository<BookingLogModel, Long> {
    List<BookingLogModel> findByBookingOrderByLoggedAtAsc(BookingModel booking);
}