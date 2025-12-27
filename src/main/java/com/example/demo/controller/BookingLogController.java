

package com.example.demo.controller;

import com.example.demo.model.BookingLog;
import com.example.demo.service.BookingLogService;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
public class BookingLogController {

    private final BookingLogService logService;

    public BookingLogController(BookingLogService logService) {
        this.logService = logService;
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<BookingLog>> logs(@PathVariable Long bookingId) {
        return ResponseEntity.ok(logService.getLogsByBooking(bookingId));
    }
}
