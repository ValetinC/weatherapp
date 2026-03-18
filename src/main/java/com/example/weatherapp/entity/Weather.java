package com.example.weatherapp.entity;

import jakarta.persistence.*;

@Entity
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private double feelsLike;
    private int humidity;
    private int pressure;

    private double windSpeed;
    private String description;

    private long timestamp;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    // getters y setters
}