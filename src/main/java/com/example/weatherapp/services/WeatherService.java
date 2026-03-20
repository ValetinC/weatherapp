package com.example.weatherapp.services;

import com.example.weatherapp.entity.*;
import com.example.weatherapp.repository.*;
import com.example.weatherapp.clients.WeatherAPIClient;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    // ✅ CREATE (con rango de fechas)
    public List<Weather> createWeather(String city, LocalDate start, LocalDate end) {

        if (start.isAfter(end)) {
            throw new RuntimeException("La fecha de inicio no puede ser mayor a la final");
        }

        // (Simplificado: lat/lon fijo, luego podés usar API de geocoding)
        
        Location location = new Location();
        location.setCity(city);
        
        Location savedLocation = locationRepository.save(location);

        List<Weather> result = new java.util.ArrayList<>();

        LocalDate current = start;

        while (!current.isAfter(end)) {

            String response = apiClient.getWeather(city);
            JSONObject json = new JSONObject(response);

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
            weather.setDate(current);
            weather.setLocation(savedLocation);

            result.add(weatherRepository.save(weather));

            current = current.plusDays(1);
        }

        return result;
    }

    // ✅ READ
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    public Weather getWeatherById(Long id) {
        return weatherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    public List<Weather> getByDateRange(LocalDate start, LocalDate end) {
        return weatherRepository.findByDateBetween(start, end);
    }

    // ✅ UPDATE
    public Weather updateWeather(Long id, Weather updated) {

        Weather weather = weatherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No encontrado"));

        weather.setTemperature(updated.getTemperature());
        weather.setDescription(updated.getDescription());
        weather.setHumidity(updated.getHumidity());

        return weatherRepository.save(weather);
    }

    // ✅ DELETE
    public void deleteWeather(Long id) {
        weatherRepository.deleteById(id);
    }
}