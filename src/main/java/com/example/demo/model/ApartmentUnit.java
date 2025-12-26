package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "apartment_units")
@Data
@NoArgsConstructor
@AllArgsConstructor
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