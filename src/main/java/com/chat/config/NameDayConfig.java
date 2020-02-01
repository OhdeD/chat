package com.chat.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class NameDayConfig {
    @Value("${nameDay.api.endpoint.today}")
    private String today;
    @Value("${nameDay.api.endpoint.tomorrow}")
    private String tomorrow;
}
