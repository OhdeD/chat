package com.chat.client.accuWeather;

import com.chat.config.AccuConfig;
import com.chat.domain.DTO.AccuWeatherDto;
import com.chat.domain.DTO.AccuWeatherLocalizationDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class AccuWeatherClient {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AccuConfig accuConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(AccuWeatherClient.class);

    public List<AccuWeatherLocalizationDto> getAccuWeatherLocalization(String localization) {
        URI url = UriComponentsBuilder.fromHttpUrl(accuConfig.getWeatherLocalizationEndpoint())
                .queryParam("apikey", accuConfig.getWeatherKey())
                .queryParam("q", localization)
                .queryParam("language", accuConfig.getWeatherLanguage()).build().encode().toUri();
        AccuWeatherLocalizationDto[] weatherResponse = restTemplate.getForObject(url, AccuWeatherLocalizationDto[].class);
        if (weatherResponse != null) {
            return Arrays.asList(weatherResponse);
        }
        LOGGER.warn("Respond from server was null");
        return new ArrayList<>();
    }

    public AccuWeatherDto getAccuWeather(String locationKey) {
        URI url = UriComponentsBuilder.fromHttpUrl(accuConfig.getWeatherEndpoint() + "/" + locationKey)
                .queryParam("apikey", accuConfig.getWeatherKey())
                .queryParam("language", accuConfig.getWeatherLanguage())
                .queryParam("details", false).build().encode().toUri();
        AccuWeatherDto[] weather = restTemplate.getForObject(url, AccuWeatherDto[].class);
        if (weather != null) {
            return Arrays.asList(weather).get(0);
        }
        LOGGER.warn("Weather obj is empty");
        return new AccuWeatherDto();
    }
}
