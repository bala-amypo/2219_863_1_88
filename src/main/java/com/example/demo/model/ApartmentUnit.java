package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Entity
@Table(name = "apartment_units")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class ApartmentUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String unitNumber;

    @Column(nullable = false)
    private Integer floor;

    @OneToOne
    @JoinColumn(name = "owner_id")
    private User owner;
}