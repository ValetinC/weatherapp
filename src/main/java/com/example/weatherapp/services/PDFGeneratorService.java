package com.example.weatherapp.services;

import com.example.weatherapp.entity.Weather;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class PDFGeneratorService {

    public byte[] generatePDF(List<Weather> data) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            Document document = new Document(new com.itextpdf.kernel.pdf.PdfDocument(writer));

            document.add(new Paragraph("Weather Report"));

            for (Weather w : data) {
                document.add(new Paragraph(
                        "City: " + w.getLocation().getCity() +
                        " | Temp: " + w.getTemperature() +
                        " | Humidity: " + w.getHumidity() +
                        " | Desc: " + w.getDescription()
                ));
            }

            document.close();

        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF");
        }

        return out.toByteArray();
    }
}
