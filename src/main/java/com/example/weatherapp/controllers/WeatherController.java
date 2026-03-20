package com.example.weatherapp.controllers;

import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.services.WeatherService;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    // ✅ CREATE
    @PostMapping
    public List<Weather> createWeather(
            @RequestParam String city,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return weatherService.createWeather(
                city,
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
    }

    // ✅ READ
    @GetMapping
    public List<Weather> getAll() {
        return weatherService.getAllWeather();
    }

    @GetMapping("/{id}")
    public Weather getById(@PathVariable Long id) {
        return weatherService.getWeatherById(id);
    }

    @GetMapping("/range")
    public List<Weather> getByRange(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        return weatherService.getByDateRange(
                LocalDate.parse(startDate),
                LocalDate.parse(endDate)
        );
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public Weather update(
            @PathVariable Long id,
            @RequestBody Weather weather
    ) {
        return weatherService.updateWeather(id, weather);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        weatherService.deleteWeather(id);
    }
}