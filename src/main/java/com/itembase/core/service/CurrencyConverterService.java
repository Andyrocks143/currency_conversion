package com.itembase.core.service;

import com.itembase.core.exception.InvalidRequestException;
import com.itembase.core.model.CurrencyConverter;
import com.itembase.core.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Slf4j
public class CurrencyConverterService {

    @Autowired
    ExchangeRateService exchangeRateService;

    public CurrencyConverter convertCurrency(CurrencyConverter currencyConverter) throws
            InvalidRequestException {

        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate();
        log.info("Exchange rates are: {}",
                exchangeRate.getRates());
        validateRequest(currencyConverter.getTo(),
                currencyConverter.getFrom(),
                exchangeRate.getRates());
        float convertedFromValueToEur = convertFromValueToEur(currencyConverter.getAmount(),
                currencyConverter.getFrom(),
                exchangeRate.getRates());

        float responseValue = convertCurrencyToGivenValue(convertedFromValueToEur,
                currencyConverter.getTo(),
                exchangeRate.getRates());

        return new CurrencyConverter(currencyConverter.getFrom(),
                currencyConverter.getTo(),
                currencyConverter.getAmount(),
                responseValue);
    }


    private void validateRequest(String toValue, String fromValue, HashMap<String, String> exchangeRate) throws
            InvalidRequestException {

        if(!exchangeRate.containsKey(toValue) &&
                (!fromValue.equalsIgnoreCase("EUR") ||
                        exchangeRate.containsKey(fromValue))) {
            throw new InvalidRequestException();
        }
    }

    private float convertFromValueToEur(float amount, String fromValue, HashMap<String, String> rates) {
        if(fromValue.equalsIgnoreCase("EUR")){
            return amount;
        }
        return amount / Float.parseFloat(rates.get(fromValue));
    }

    private float convertCurrencyToGivenValue(float amount, String toValue, HashMap<String, String> rates) {
        return amount * Float.parseFloat(rates.get(toValue));
    }
}
