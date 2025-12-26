package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApartmentUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String unitNumber;

    private Integer floor;

    @OneToOne
    private User owner;

    @PrePersist
    public void validate() {
        if (floor < 0) {
            throw new IllegalArgumentException("Floor must be >= 0");
        }
    }
}
