package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_logs")
@Data
@NoArgsConstructor
public class BookingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(nullable = false)
    private String logMessage;

    @Column(nullable = false)
    private LocalDateTime loggedAt;

    public BookingLog(Long id, Booking booking, String logMessage) {
        this.id = id;
        this.booking = booking;
        this.logMessage = logMessage;
        this.loggedAt = LocalDateTime.now();
    }

    @PrePersist
    public void onCreate() {
        this.loggedAt = LocalDateTime.now();
    }
}
