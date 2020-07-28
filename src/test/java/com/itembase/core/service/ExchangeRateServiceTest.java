package com.itembase.core.service;

import com.itembase.core.client.ExchangeRateClient;
import com.itembase.core.exception.ExchangeRateNotFoundException;
import com.itembase.utils.MockResponses;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ExchangeRateServiceTest {

    @Mock
    ExchangeRateClient exchangeRateClient;

    @InjectMocks
    ExchangeRateService exchangeRateService;

    @Before
    public void setup() {
        initMocks(this);
    }

    @Test
    public void getExchangeRate_happyPath_returnsExchangeRate() {
        when(exchangeRateClient.getExchangeRate()).thenReturn(MockResponses.getMockExchangeRate());

        assertEquals(exchangeRateService.getExchangeRate().getBase(), MockResponses.getMockExchangeRate().getBase());
        assertEquals(exchangeRateService.getExchangeRate().getDate(), MockResponses.getMockExchangeRate().getDate());
        assertEquals(exchangeRateService.getExchangeRate().getRates().size(), MockResponses.getMockExchangeRate().getRates().size());
    }

    @Test
    public void getExchangeRate_unhappyPath_throwsException() {
        when(exchangeRateClient.getExchangeRate()).thenThrow(new ExchangeRateNotFoundException("sample error message"));

        assertThrows(ExchangeRateNotFoundException.class, () -> {
            exchangeRateService.getExchangeRate();
        });
    }
}
