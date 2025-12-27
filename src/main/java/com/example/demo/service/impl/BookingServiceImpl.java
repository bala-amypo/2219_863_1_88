package com.example.demo.service.impl;

import com.example.demo.exception.ConflictException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.BookingLogService;
import com.example.demo.service.BookingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;
    private final BookingLogService bookingLogService;

    public BookingServiceImpl(BookingRepository bookingRepository,
                              FacilityRepository facilityRepository,
                              UserRepository userRepository,
                              BookingLogService bookingLogService) {
        this.bookingRepository = bookingRepository;
        this.facilityRepository = facilityRepository;
        this.userRepository = userRepository;
        this.bookingLogService = bookingLogService;
    }

    @Override
    public BookingModel createBooking(Long facilityId, Long userId, BookingModel booking) {

        FacilityModel facility = facilityRepository.findById(facilityId).orElseThrow();
        UserModel user = userRepository.findById(userId).orElseThrow();

        List<BookingModel> conflicts =
                bookingRepository.findByFacilityAndStartTimeLessThanAndEndTimeGreaterThan(
                        facility, booking.getEndTime(), booking.getStartTime());

        if (!conflicts.isEmpty()) {
            throw new ConflictException("Booking conflict");
        }

        booking.setFacility(facility);
        booking.setUser(user);

        BookingModel saved = bookingRepository.save(booking);
        bookingLogService.addLog(saved.getId(), "Booking created");

        return saved;
    }

    @Override
    public BookingModel cancelBooking(Long bookingId) {
        BookingModel booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus("CANCELLED");
        bookingLogService.addLog(bookingId, "Booking cancelled");
        return bookingRepository.save(booking);
    }

    @Override
    public BookingModel getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow();
    }
}