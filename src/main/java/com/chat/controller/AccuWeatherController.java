package com.chat.controller;

import com.chat.client.accuWeather.AccuWeatherClient;
import com.chat.domain.DTO.AccuWeatherDto;
import com.chat.service.ChatUserDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/chat/{userId}")
public class AccuWeatherController {
    @Autowired
    AccuWeatherClient accuWeatherClient;
    @Autowired
    ChatUserDbService chatUserDbService;

    @GetMapping("/localization")
    public String getAccuLocalization(@PathVariable("userId") Long userId) {
        String localization = chatUserDbService.findCity(userId);
        return accuWeatherClient.getAccuWeatherLocalization(localization).get(0).getKey();
    }

    @GetMapping("/weather")
    public AccuWeatherDto getAccuWeather(@PathVariable("userId") Long userId) {
        String locationKey = getAccuLocalization(userId);
        return accuWeatherClient.getAccuWeather(locationKey);
    }

}
