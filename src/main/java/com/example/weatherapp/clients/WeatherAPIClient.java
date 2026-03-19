package com.example.weatherapp.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherAPIClient {

    private final RestTemplate restTemplate;

    @Value("${weather.api.key}")
    private String apiKey;

    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public WeatherAPIClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getWeather(double lat, double lon) {
        try {
            String url = BASE_URL + "?lat=" + lat + "&lon=" + lon +
                         "&appid=" + apiKey + "&units=metric";

            return restTemplate.getForObject(url, String.class);

        } catch (Exception e) {
            throw new RuntimeException("Error al consumir la API del clima");
        }
    }
}