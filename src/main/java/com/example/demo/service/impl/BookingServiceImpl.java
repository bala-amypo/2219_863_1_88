package com.example.demo.service.impl;

import com.example.demo.model.Booking;
import com.example.demo.repository.BookingRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingServiceImpl {

    private final BookingRepository bookingRepository;

    public BookingServiceImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    public Booking confirmBooking(Booking booking) {
        booking.setStatus(Booking.STATUS_CONFIRMED);
        return bookingRepository.save(booking);
    }
}
