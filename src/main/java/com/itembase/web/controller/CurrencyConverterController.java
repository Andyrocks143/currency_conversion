package com.itembase.web.controller;

import com.itembase.core.exception.InvalidRequestException;
import com.itembase.core.model.CurrencyConverter;
import com.itembase.core.service.CurrencyConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency")
@Slf4j
public class CurrencyConverterController {

    @Autowired
    CurrencyConverterService currencyConverterService;

    @PostMapping(value = "/convert", produces = "application/json")
    public CurrencyConverter getExchangeRates(@RequestBody CurrencyConverter currencyConverter) throws
            InvalidRequestException {

        log.info("Incoming request to convert amount: [{}],  from [{}] to [{}}",
                currencyConverter.getAmount(),
                currencyConverter.getFrom(),
                currencyConverter.getTo());
        return currencyConverterService.convertCurrency(currencyConverter);
    }
}
