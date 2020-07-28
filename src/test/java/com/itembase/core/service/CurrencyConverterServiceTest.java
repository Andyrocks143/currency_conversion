package com.itembase.core.service;

import com.itembase.core.exception.InvalidRequestException;
import com.itembase.core.model.CurrencyConverter;
import com.itembase.utils.MockResponses;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class CurrencyConverterServiceTest {

    @Mock
    ExchangeRateService exchangeRateService;

    @InjectMocks
    CurrencyConverterService currencyConverterService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void convertCurrency_validInputEuros_returnsAmount() throws
            InvalidRequestException {

        CurrencyConverter requestCurrencyConverter = new CurrencyConverter("EUR", "USD", 14);

        when(exchangeRateService.getExchangeRate()).thenReturn(MockResponses.getMockExchangeRate());

        CurrencyConverter responseCurrencyConverter = currencyConverterService.convertCurrency(requestCurrencyConverter);

        assertEquals(responseCurrencyConverter.getFrom(), requestCurrencyConverter.getFrom());
        assertEquals(responseCurrencyConverter.getTo(), requestCurrencyConverter.getTo());
        assertEquals(responseCurrencyConverter.getAmount(), requestCurrencyConverter.getAmount());
        assertEquals(responseCurrencyConverter.getConverted(), 16.271472930908203);
    }

    @Test
    public void convertCurrency_validInput_NonEuros_returnsAmount() throws
            InvalidRequestException {

        CurrencyConverter requestCurrencyConverter = new CurrencyConverter("USD", "EUR", 14);

        when(exchangeRateService.getExchangeRate()).thenReturn(MockResponses.getMockExchangeRate());

        CurrencyConverter responseCurrencyConverter = currencyConverterService.convertCurrency(requestCurrencyConverter);

        assertEquals(responseCurrencyConverter.getFrom(), requestCurrencyConverter.getFrom());
        assertEquals(responseCurrencyConverter.getTo(), requestCurrencyConverter.getTo());
        assertEquals(responseCurrencyConverter.getAmount(), requestCurrencyConverter.getAmount());
        assertEquals(responseCurrencyConverter.getConverted(), 12.045621871948242);
    }

//    Negative Scenarios cannot have unit tests as There is a NotBlank claus during the request validator in the controller class
}
