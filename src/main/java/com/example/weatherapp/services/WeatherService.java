package com.example.weatherapp.services;

import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.repository.locationRepository;
import com.example.weatherapp.repository.weatherRepository;
import com.example.weatherapp.clients.WeatherAPIClient;
import com.example.weatherapp.entity.Location;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherAPIClient apiClient;
    private final weatherRepository weatherRepository;
    private final locationRepository locationRepository;

    public WeatherService(WeatherAPIClient apiClient,
                          weatherRepository weatherRepository,
                          locationRepository locationRepository) {
        this.apiClient = apiClient;
        this.weatherRepository = weatherRepository;
        this.locationRepository = locationRepository;
    }

    public Weather getWeather(double lat, double lon) {

        String response = apiClient.getWeather(lat, lon);
        JSONObject json = new JSONObject(response);

        Location location = new Location();
        location.setLatitude(lat);
        location.setLongitude(lon);
        location.setTimestamp(System.currentTimeMillis());

        Location savedLocation = locationRepository.save(location);

        Weather weather = new Weather();
        weather.setTemperature(json.getJSONObject("main").getDouble("temp"));
        weather.setFeelsLike(json.getJSONObject("main").getDouble("feels_like"));
        weather.setHumidity(json.getJSONObject("main").getInt("humidity"));
        weather.setPressure(json.getJSONObject("main").getInt("pressure"));
        weather.setDescription(
                json.getJSONArray("weather")
                        .getJSONObject(0)
                        .getString("description")
        );
        weather.setWindSpeed(json.getJSONObject("wind").getDouble("speed"));
        weather.setTimestamp(System.currentTimeMillis());
        weather.setLocation(savedLocation);

        return weatherRepository.save(weather);
    }
}