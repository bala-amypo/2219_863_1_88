package com.example.demo.controller;

import com.example.demo.model.BookingLog;
import com.example.demo.service.BookingLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class BookingLogController {

    private final BookingLogService bookingLogService;

    public BookingLogController(BookingLogService bookingLogService) {
        this.bookingLogService = bookingLogService;
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<BookingLog>> getLogsByBooking(@PathVariable Long bookingId) {
        return ResponseEntity.ok(bookingLogService.getLogsByBooking(bookingId));
    }
}
