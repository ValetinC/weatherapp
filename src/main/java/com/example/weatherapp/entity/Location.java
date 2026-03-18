package com.example.weatherapp.entity;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double latitude;
    private double longitude;

    private String city;
    private String country;
    private String state;
    private String timezone;

    private long timestamp;

    
}
