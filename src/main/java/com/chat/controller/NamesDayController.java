package com.chat.controller;

import com.chat.client.namesDay.NameDayClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/nameday")
public class NamesDayController {
    @Autowired
    NameDayClient nameDayClient;

    @GetMapping("/today")
    public String getTodayNames(){
        return nameDayClient.getTodayNames();
    }

    @GetMapping("/tomorrow")
    public String getTomorrowNames(){
        return nameDayClient.getTomorrowNames();
    }
}
