package com.example.demo.service.impl;

import com.example.demo.exception.ConflictException;
import com.example.demo.model.Booking;
import com.example.demo.model.BookingLog;
import com.example.demo.model.Facility;
import com.example.demo.model.User;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.BookingLogRepository;
import com.example.demo.repository.FacilityRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final FacilityRepository facilityRepository;
    private final UserRepository userRepository;
    private final BookingLogRepository bookingLogRepository;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository,
                              FacilityRepository facilityRepository,
                              UserRepository userRepository,
                              BookingLogRepository bookingLogRepository) {

        this.bookingRepository = bookingRepository;
        this.facilityRepository = facilityRepository;
        this.userRepository = userRepository;
        this.bookingLogRepository = bookingLogRepository;
    }

    @Override
    public Booking createBooking(Long facilityId, Long userId, Booking booking) {

        Facility facility = facilityRepository.findById(facilityId).orElseThrow();
        User user = userRepository.findById(userId).orElseThrow();

        booking.setFacility(facility);
        booking.setUser(user);
        booking.setStatus(Booking.STATUS_CONFIRMED);

        List<Booking> conflicts =
                bookingRepository.findByFacilityAndStartTimeLessThanAndEndTimeGreaterThan(
                        facility,
                        booking.getEndTime(),
                        booking.getStartTime()
                );

        if (!conflicts.isEmpty()) {
            throw new ConflictException("Booking conflicts with existing booking");
        }

        Booking saved = bookingRepository.save(booking);

        BookingLog log = new BookingLog(
                null,
                saved,
                "Booking created",
                LocalDateTime.now()
        );
        bookingLogRepository.save(log);

        return saved;
    }

    @Override
    public Booking cancelBooking(Long bookingId) {

        Booking booking = bookingRepository.findById(bookingId).orElseThrow();
        booking.setStatus(Booking.STATUS_CANCELLED);

        Booking saved = bookingRepository.save(booking);

        BookingLog log = new BookingLog(
                null,
                saved,
                "Booking cancelled",
                LocalDateTime.now()
        );
        bookingLogRepository.save(log);

        return saved;
    }

    @Override
    public Booking getBooking(Long bookingId) {
        return bookingRepository.findById(bookingId).orElseThrow();
    }
}
