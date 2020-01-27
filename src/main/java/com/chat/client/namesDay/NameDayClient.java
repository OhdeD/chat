package com.chat.client.namesDay;

import com.chat.config.NameDayConfig;
import com.chat.domain.DTO.NamedayDataDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Controller
public class NameDayClient {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    NameDayConfig nameDayConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(NameDayClient.class);

    public String getTodaysNames() {
        URI url = UriComponentsBuilder.fromHttpUrl(nameDayConfig.getToday())
                .queryParam("country", "pl")
                .queryParam("timezone", "Europe/Amsterdam").build().encode().toUri();
        NamedayDataDto names = restTemplate.getForObject(url, NamedayDataDto.class);
        if (names!=null)return names.getData().get(0).getNamedays().getPl();
        LOGGER.warn("Wrong response from server");
        return "";
    }

    public String getTomorrowNames() {
        URI url = UriComponentsBuilder.fromHttpUrl(nameDayConfig.getTomorrow())
                .queryParam("country", "pl")
                .queryParam("timezone", "Europe/Amsterdam").build().encode().toUri();
        NamedayDataDto names = restTemplate.getForObject(url, NamedayDataDto.class);
        if (names!=null)return names.getData().get(0).getNamedays().getPl();
        LOGGER.warn("Wrong response from server");
        return "";
    }
}
