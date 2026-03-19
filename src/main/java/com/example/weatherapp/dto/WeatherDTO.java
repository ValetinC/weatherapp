package com.example.weatherapp.dto;

public class WeatherDTO {

    private double temperature;
    private double feelsLike;
    private int humidity;
    private String description;
    private double windSpeed;

    public WeatherDTO(double temperature, double feelsLike, int humidity,
                      String description, double windSpeed) {
        this.temperature = temperature;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.description = description;
        this.windSpeed = windSpeed;
    }

    public double getTemperature() { return temperature; }
    public double getFeelsLike() { return feelsLike; }
    public int getHumidity() { return humidity; }
    public String getDescription() { return description; }
    public double getWindSpeed() { return windSpeed; }
}