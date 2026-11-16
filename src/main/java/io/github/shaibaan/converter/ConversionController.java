package io.github.shaibaan.converter;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class ConversionController {
    @GetMapping("/temperature")
    public Conversion temperature(@RequestParam double celsius) {
        validate(celsius);
        return new Conversion(celsius, "celsius", rounded(celsius * 9 / 5 + 32), "fahrenheit");
    }

    @GetMapping("/distance")
    public Conversion distance(@RequestParam double kilometres) {
        validate(kilometres);
        if (kilometres < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Distance cannot be negative");
        }
        return new Conversion(kilometres, "kilometres", rounded(kilometres * 0.621371), "miles");
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "UP");
    }

    private static void validate(double value) {
        if (!Double.isFinite(value) || Math.abs(value) > 1_000_000_000) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Use a finite value within +/- 1 billion");
        }
    }

    private static double rounded(double value) {
        return BigDecimal.valueOf(value).setScale(4, RoundingMode.HALF_UP).doubleValue();
    }

    public record Conversion(double input, String inputUnit, double result, String outputUnit) {}
}
