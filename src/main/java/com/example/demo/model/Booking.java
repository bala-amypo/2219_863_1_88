package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
@Data
@NoArgsConstructor
public class Booking {

    public static final String STATUS_CONFIRMED = "CONFIRMED";
    public static final String STATUS_CANCELLED = "CANCELLED";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "facility_id")
    private Facility facility;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    @Column
    private String status = STATUS_CONFIRMED; 
    
    public Booking(Long id, Facility facility, User user,
                   LocalDateTime startTime, LocalDateTime endTime, String status) {
        this.id = id;
        this.facility = facility;
        this.user = user;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = (status == null) ? STATUS_CONFIRMED : status; // ensure default
    }

    @PrePersist
    public void prePersist() {
        if (this.status == null) {
            this.status = STATUS_CONFIRMED;
        }
    }
}
