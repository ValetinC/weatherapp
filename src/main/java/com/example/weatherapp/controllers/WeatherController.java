package com.example.weatherapp.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public Weather getWeather(
        @RequestParam double lat,
        @RequestParam double lon
    ) {
        return weatherService.getWeather(lat, lon);
    }
}
