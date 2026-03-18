package com.example.weatherapp.clients;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherAPIClient {

    private final String API_KEY = "TU_API_KEY";
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeather(double lat, double lon) {
        String url = BASE_URL + "?lat=" + lat + "&lon=" + lon +
                     "&appid=" + API_KEY + "&units=metric";

        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(url, String.class);
    }
}