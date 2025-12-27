package com.example.demo.service;

import com.example.demo.model.BookingModel;

public interface BookingService {
    BookingModel createBooking(Long facilityId, Long userId, BookingModel booking);
    BookingModel cancelBooking(Long bookingId);
    BookingModel getBooking(Long bookingId);
}
