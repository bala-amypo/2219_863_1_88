package com.example.demo.model;

import com.example.demo.exception.ConflictException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Facility facility;

    @ManyToOne
    private User user;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String status = "CONFIRMED";

    @PrePersist
    public void validate() {
        if (startTime.isAfter(endTime) || startTime.equals(endTime)) {
            throw new ConflictException("Booking startTime must be before endTime");
        }
    }
}
