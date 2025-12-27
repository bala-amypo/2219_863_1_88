package com.example.demo.service;

import com.example.demo.model.BookingLogModel;

import java.util.List;

public interface BookingLogService {
    BookingLogModel addLog(Long bookingId, String message);
    List<BookingLogModel> getLogsByBooking(Long bookingId);
}