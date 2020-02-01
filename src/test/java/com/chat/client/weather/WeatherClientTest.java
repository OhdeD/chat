package com.chat.client.weather;

import com.chat.client.accuWeather.AccuWeatherClient;
import com.chat.config.AccuConfig;
import com.chat.domain.DTO.AccuWeatherDto;
import com.chat.domain.DTO.AccuWeatherLocalizationDto;
import com.chat.domain.DTO.Metric;
import com.chat.domain.DTO.Temperature;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WeatherClientTest {

    @InjectMocks
    AccuWeatherClient accuWeatherClient;
    @Mock
    RestTemplate restTemplate;
    @Mock
    AccuConfig accuConfig;

    @Test
    public void shouldFetchAccuWeatherLocalization() throws URISyntaxException {
        //Given
        when(accuConfig.getWeatherLocalizationEndpoint()).thenReturn("http://test.com");
        when(accuConfig.getWeatherKey()).thenReturn("testKey");
        when(accuConfig.getWeatherLanguage()).thenReturn("testLanguage");
        String localization = "testLocalization";
        URI url = new URI("http://test.com?apikey=testKey&q=testLocalization&language=testLanguage&");

        AccuWeatherLocalizationDto[] accuWeatherLocalizationDtos = new AccuWeatherLocalizationDto[1];
        accuWeatherLocalizationDtos[0] = new AccuWeatherLocalizationDto("testKey");
        when(restTemplate.getForObject(any(),any())).thenReturn(accuWeatherLocalizationDtos);

        //When
        List<AccuWeatherLocalizationDto> list = accuWeatherClient.getAccuWeatherLocalization(localization);

        //Then
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void shoudFetchAccuWeather() throws URISyntaxException {
        //Given
        when(accuConfig.getWeatherEndpoint()).thenReturn("http://test.com");
        when(accuConfig.getWeatherKey()).thenReturn("testKey");
        when(accuConfig.getWeatherLanguage()).thenReturn("testLanguage");
        String locationKey = "testLocationKey";
        URI url = new URI("http://test.com/testLocationKey?apikey=testKey&q=testLocalization&language=testLanguage&");
        AccuWeatherDto[] weather = new AccuWeatherDto[1];
        weather[0]= new AccuWeatherDto("testWeather", new Temperature(new Metric(15.2)));
        when(restTemplate.getForObject(any(),any())).thenReturn(weather);
        //When
        AccuWeatherDto w = accuWeatherClient.getAccuWeather(locationKey);
        double acctual = w.getTemperature().getMetric().getValue();

        //Then
        Assert.assertEquals(15.2, acctual, 0.1);
    }

}
