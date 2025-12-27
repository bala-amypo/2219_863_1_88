/*package com.example.demo.controller;

import com.example.demo.model.BookingLog;
import com.example.demo.service.BookingLogService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/logs")
@Tag(name = "BookingLog", description = "Booking log management endpoints")
public class BookingLogController {
    
    private final BookingLogService bookingLogService;
    
    public BookingLogController(BookingLogService bookingLogService) {
        this.bookingLogService = bookingLogService;
    }
    
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<List<BookingLog>> getLogsByBooking(@PathVariable Long bookingId) {
        List<BookingLog> logs = bookingLogService.getLogsByBooking(bookingId);
        return ResponseEntity.ok(logs);
    }
} */

package com.example.demo.controller;

import com.example.demo.model.BookingLogModel;
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
    public ResponseEntity<List<BookingLogModel>> logs(@PathVariable Long bookingId) {
        return ResponseEntity.ok(logService.getLogsByBooking(bookingId));
    }
}
