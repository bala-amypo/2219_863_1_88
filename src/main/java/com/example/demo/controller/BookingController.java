package com.example.demo.controller;

import com.example.demo.model.Booking;
import com.example.demo.service.BookingService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Booking", description = "Booking management endpoints")
public class BookingController {
    
    private final BookingService bookingService;
    
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    @PostMapping("/{facilityId}/{userId}")
    public ResponseEntity<Booking> createBooking(@PathVariable Long facilityId, 
                                               @PathVariable Long userId, 
                                               @RequestBody Booking booking) {
        Booking created = bookingService.createBooking(facilityId, userId, booking);
        return ResponseEntity.ok(created);
    }
    
    @PutMapping("/cancel/{bookingId}")
    public ResponseEntity<Booking> cancelBooking(@PathVariable Long bookingId) {
        Booking cancelled = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(cancelled);
    }
    
    @GetMapping("/{bookingId}")
    public ResponseEntity<Booking> getBooking(@PathVariable Long bookingId) {
        Booking booking = bookingService.getBooking(bookingId);
        return ResponseEntity.ok(booking);
    }
}