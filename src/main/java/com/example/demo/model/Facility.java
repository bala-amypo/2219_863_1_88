package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Data 
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facilities")
public class Facility{
    @Id
    @GeneratedValue(strategy="GenerationType.IDENTITY")
    private Long id;

    @Column(nullable=false)
}