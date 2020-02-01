package com.chat.client.namesDay;

import com.chat.config.NameDayConfig;
import com.chat.domain.DTO.Data;
import com.chat.domain.DTO.NamedayDataDto;
import com.chat.domain.DTO.Namedays;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class NameDayClientTest {
    @InjectMocks
    NameDayClient nameDayClient;
    @Mock
    RestTemplate restTemplate;
    @Mock
    NameDayConfig nameDayConfig;

    @Test
    public void shouldFetchTodayNames() throws URISyntaxException {
        //Given
        when(nameDayConfig.getToday()).thenReturn("https://test.com");
        Namedays n = new Namedays("test, test, test");
        Data d = new Data(n);
        NamedayDataDto nd = new NamedayDataDto(new ArrayList<>());
        nd.getData().add(d);
        URI url = new URI("http://test.com?country=pl&timezone=Europe%2FAmsterdam");
        when(restTemplate.getForObject(any(),any())).thenReturn(nd);

        //When
        String names = nameDayClient.getTodayNames();

        //Then
        Assert.assertEquals("test, test, test", names);
    }
    @Test
    public void shouldFetchTomorrowNames() throws URISyntaxException {
        //Given
        when(nameDayConfig.getTomorrow()).thenReturn("https://test.com");
        Namedays n = new Namedays("test, test, test");
        Data d = new Data(n);
        NamedayDataDto nd = new NamedayDataDto(new ArrayList<>());
        nd.getData().add(d);
        URI url = new URI("http://test.com?country=pl&timezone=Europe%2FAmsterdam");
        when(restTemplate.getForObject(any(),any())).thenReturn(nd);

        //When
        String names = nameDayClient.getTomorrowNames();

        //Then
        Assert.assertEquals("test, test, test", names);
    }



}

