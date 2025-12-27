package com.example.demo.service.impl;

import com.example.demo.model.BookingLog;
import com.example.demo.model.Booking;
import com.example.demo.repository.BookingLogRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.BookingLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingLogServiceImpl implements BookingLogService {

    private final BookingLogRepository logRepository;
    private final BookingRepository bookingRepository;

    public BookingLogServiceImpl(BookingLogRepository logRepository,
                                 BookingRepository bookingRepository) {
        this.logRepository = logRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingLog addLog(Long bookingId, String message) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        BookingLog log = new BookingLog();
        log.setBooking(booking);
        log.setLogMessage(message);
        log.setLoggedAt(LocalDateTime.now());

        return logRepository.save(log);
    }

    @Override
    public List<BookingLog> getLogsByBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return logRepository.findByBookingOrderByLoggedAtAsc(booking);
    }
}