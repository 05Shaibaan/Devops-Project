package io.github.shaibaan.converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ConversionControllerTest {
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(new ConversionController()).build();
    }

    @Test
    void convertsBoilingWaterToFahrenheit() throws Exception {
        mvc.perform(get("/temperature").param("celsius", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(212.0))
                .andExpect(jsonPath("$.outputUnit").value("fahrenheit"));
    }

    @Test
    void convertsNegativeTemperature() throws Exception {
        mvc.perform(get("/temperature").param("celsius", "-40"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.result").value(-40.0));
    }

    @Test
    void convertsFiveKilometresToMiles() throws Exception {
        mvc.perform(get("/distance").param("kilometres", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.result").value(3.1069))
                .andExpect(jsonPath("$.inputUnit").value("kilometres"));
    }

    @Test
    void handlesZeroDistance() throws Exception {
        mvc.perform(get("/distance").param("kilometres", "0"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.result").value(0.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"NaN", "Infinity", "1e309", "1000000001", "abc"})
    void rejectsInvalidTemperature(String value) throws Exception {
        mvc.perform(get("/temperature").param("celsius", value)).andExpect(status().isBadRequest());
    }

    @ParameterizedTest
    @ValueSource(strings = {"-1", "NaN", "-Infinity", "1000000001"})
    void rejectsInvalidDistance(String value) throws Exception {
        mvc.perform(get("/distance").param("kilometres", value)).andExpect(status().isBadRequest());
    }

    @Test
    void requiresTheInputParameter() throws Exception {
        mvc.perform(get("/temperature")).andExpect(status().isBadRequest());
        mvc.perform(get("/distance")).andExpect(status().isBadRequest());
    }

    @Test
    void returnsHealthStatus() throws Exception {
        mvc.perform(get("/health")).andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
