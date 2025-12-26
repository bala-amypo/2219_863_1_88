package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String description;
    private String openTime;
    private String closeTime;

    @PrePersist
    public void validateTime() {
        if (openTime.compareTo(closeTime) >= 0) {
            throw new IllegalArgumentException("Open time must be before close time");
        }
    }
}
