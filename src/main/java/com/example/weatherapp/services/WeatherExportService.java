package com.example.weatherapp.services;

import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.repository.weatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WeatherExportService {

    private final weatherRepository weatherRepository;

    public WeatherExportService(weatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    // 📌 Obtener todos los datos
    public List<Weather> getAllWeather() {
        return weatherRepository.findAll();
    }

    // 📌 Exportar a CSV
    public String exportToCSV() {
        List<Weather> data = weatherRepository.findAll();

        String header = "id,temperature,feelsLike,humidity,pressure,description,windSpeed\n";

        String body = data.stream()
                .map(w -> w.getId() + "," +
                        w.getTemperature() + "," +
                        w.getFeelsLike() + "," +
                        w.getHumidity() + "," +
                        w.getPressure() + "," +
                        w.getDescription() + "," +
                        w.getWindSpeed()
                )
                .collect(Collectors.joining("\n"));

        return header + body;
    }
}
