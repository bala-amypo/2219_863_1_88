package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bookings")
public class Booking{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    
}