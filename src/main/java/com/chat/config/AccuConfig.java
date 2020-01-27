package com.chat.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AccuConfig {
    @Value("${accuWeather.api.endpoint.locallization}")
    private String weatherLocalizationEndpoint;
    @Value("${accuWeather.app.key}")
    private String weatherKey;
    @Value("${accuWeather.app.language}")
    private String weatherLanguage;
    @Value("${accuWeather.api.endpoint.weather}")
    private String weatherEndpoint;
}
