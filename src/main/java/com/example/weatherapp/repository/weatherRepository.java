package com.example.weatherapp.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.weatherapp.entity.Weather;

public interface weatherRepository extends JpaRepository<Weather, Long> {
        List<Weather> findByDateBetween(LocalDate start, LocalDate end);

}