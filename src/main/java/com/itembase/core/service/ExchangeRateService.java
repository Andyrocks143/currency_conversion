package com.itembase.core.service;

import com.itembase.core.client.ExchangeRateClient;
import com.itembase.core.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExchangeRateService {

    @Autowired
    ExchangeRateClient exchangeRateClient;

    public ExchangeRate getExchangeRate() {
        log.info("ExchangeRate service call for fetching Exchange rate");
        return exchangeRateClient.getExchangeRate();
    }

}
