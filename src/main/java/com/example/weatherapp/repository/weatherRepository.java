package com.example.weatherapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.weatherapp.entity.Weather;

public interface weatherRepository extends JpaRepository<Weather, Long> {
}