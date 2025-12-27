package com.example.demo.service.impl;

import com.example.demo.model.BookingLogModel;
import com.example.demo.model.BookingModel;
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
    public BookingLogModel addLog(Long bookingId, String message) {

        BookingModel booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        BookingLogModel log = new BookingLogModel();
        log.setBooking(booking);
        log.setLogMessage(message);
        log.setLoggedAt(LocalDateTime.now());

        return logRepository.save(log);
    }

    @Override
    public List<BookingLogModel> getLogsByBooking(Long bookingId) {

        BookingModel booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        return logRepository.findByBookingOrderByLoggedAtAsc(booking);
    }
}