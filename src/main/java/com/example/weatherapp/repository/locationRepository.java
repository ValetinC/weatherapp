package com.example.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.weatherapp.entity.Location;

public interface locationRepository extends JpaRepository<Location, Long> {
    
}
