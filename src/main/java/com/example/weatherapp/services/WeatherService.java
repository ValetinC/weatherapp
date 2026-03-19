package com.example.weatherapp.services;

import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.entity.Location;
import com.example.weatherapp.repository.weatherRepository;
import com.example.weatherapp.repository.locationRepository;
import com.example.weatherapp.clients.WeatherAPIClient;
import com.example.weatherapp.dto.WeatherDTO;

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

    public WeatherDTO getWeather(double lat, double lon) {

        // 🔹 Validación
        if (lat < -90 || lat > 90 || lon < -180 || lon > 180) {
            throw new IllegalArgumentException("Coordenadas inválidas");
        }

        System.out.println("Consultando clima para: " + lat + ", " + lon);

        try {
            String response = apiClient.getWeather(lat, lon);
            JSONObject json = new JSONObject(response);

            // 🔹 Guardar ubicación
            Location location = new Location();
            location.setLatitude(lat);
            location.setLongitude(lon);
            location.setTimestamp(System.currentTimeMillis());

            Location savedLocation = locationRepository.save(location);

            // 🔹 Guardar clima
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

            weatherRepository.save(weather);

            // 🔹 Retornar DTO (NO entidad)
            return new WeatherDTO(
                    weather.getTemperature(),
                    weather.getFeelsLike(),
                    weather.getHumidity(),
                    weather.getDescription(),
                    weather.getWindSpeed()
            );

        } catch (Exception e) {
            throw new RuntimeException("Error procesando datos del clima");
        }
    }
}