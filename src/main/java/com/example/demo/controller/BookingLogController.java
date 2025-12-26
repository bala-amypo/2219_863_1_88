package com.example.demo.controller;

import com.example.demo.model.BookingLog;
import com.example.demo.service.BookingLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class BookingLogController {

    private final BookingLogService service;

    public BookingLogController(BookingLogService service) {
        this.service = service;
    }

    @GetMapping("/booking/{bookingId}")
    public List<BookingLog> logs(@PathVariable Long bookingId) {
        return service.getLogsByBooking(bookingId);
    }
}
