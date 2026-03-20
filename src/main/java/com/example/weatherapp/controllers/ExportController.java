package com.example.weatherapp.controllers;

import com.example.weatherapp.entity.Weather;
import com.example.weatherapp.services.WeatherExportService;
import com.example.weatherapp.services.PDFGeneratorService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/export")
public class ExportController {

    private final WeatherExportService exportService;
    private final PDFGeneratorService pdfService;

    public ExportController(WeatherExportService exportService,
                            PDFGeneratorService pdfService) {
        this.exportService = exportService;
        this.pdfService = pdfService;
    }

    // 📌 JSON (automático con Spring)
    @GetMapping("/json")
    public List<Weather> exportJSON() {
        return exportService.getAllWeather();
    }

    // 📌 CSV
    @GetMapping("/csv")
    public ResponseEntity<String> exportCSV() {

        String csvData = exportService.exportToCSV();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=weather.csv")
                .contentType(MediaType.TEXT_PLAIN)
                .body(csvData);
    }

    // 📌 PDF
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportPDF() {

        List<Weather> data = exportService.getAllWeather();
        byte[] pdf = pdfService.generatePDF(data);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=weather.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}
