package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Booking;
import com.example.demo.model.BookingLog;
import com.example.demo.repository.BookingLogRepository;
import com.example.demo.repository.BookingRepository;
import com.example.demo.service.BookingLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingLogServiceImpl implements BookingLogService {

    private final BookingLogRepository bookingLogRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingLogServiceImpl(BookingLogRepository bookingLogRepository,
                                 BookingRepository bookingRepository) {
        this.bookingLogRepository = bookingLogRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public BookingLog addLog(Long bookingId, String message) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        BookingLog log = new BookingLog();
        log.setBooking(booking);
        log.setLogMessage(message);
        return bookingLogRepository.save(log);
    }

    @Override
    public List<BookingLog> getLogsByBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        return bookingLogRepository.findByBookingOrderByLoggedAtAsc(booking);
    }
}
