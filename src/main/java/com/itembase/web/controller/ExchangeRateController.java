package com.itembase.web.controller;


import com.itembase.core.model.ExchangeRate;
import com.itembase.core.service.ExchangeRateService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/utils")
@Slf4j
@Api(tags = "support :: Exchange rate controller")
public class ExchangeRateController {

    @Autowired
    ExchangeRateService exchangeRateService;

    @PostMapping(value = "/getexchangerate", produces = "application/json")
    public ExchangeRate getExchangeRate() {

        log.info("Incoming request to find exchange rate");
        return exchangeRateService.getExchangeRate();
    }
}
