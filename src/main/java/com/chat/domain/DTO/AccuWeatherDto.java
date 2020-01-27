package com.chat.domain.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccuWeatherDto {
    @JsonProperty("WeatherText")
    private String text;
    @JsonProperty("Temperature")
    private Temperature temperature;
}
