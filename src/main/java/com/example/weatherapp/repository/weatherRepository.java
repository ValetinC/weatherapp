package com.example.weatherapp.repository;

import com.example.weatherapp.entity.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface weatherRepository extends JpaRepository<Weather, Long> {
}
