package com.example.weatherapp.controllers;

import org.springframework.web.bind.annotation.*;

import com.example.weatherapp.dto.WeatherDTO;
import com.example.weatherapp.services.WeatherService;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherDTO getWeather(
            @RequestParam double lat,
            @RequestParam double lon
    ) {
        return weatherService.getWeather(lat, lon);
    }
}