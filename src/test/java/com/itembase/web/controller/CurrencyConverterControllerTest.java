package com.itembase.web.controller;

import com.itembase.core.exception.ExchangeRateNotFoundException;
import com.itembase.core.service.CurrencyConverterService;
import com.itembase.utils.MockResponses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CurrencyConverterController.class)
public class CurrencyConverterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CurrencyConverterService currencyConverterService;

    @Test
    public void getExchangeRate_returns405() throws Exception {
        when(currencyConverterService.convertCurrency(any())).thenThrow(ExchangeRateNotFoundException.class);

        mockMvc.perform(post("/currency/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(MockResponses.requestCurrencyConverter))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExchangeRate_returns200() throws Exception {
        when(currencyConverterService.convertCurrency(any())).thenReturn(MockResponses.getMockCurrencyConverter());

        mockMvc.perform(post("/currency/convert")
                .contentType(MediaType.APPLICATION_JSON)
                .content(MockResponses.requestCurrencyConverter))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
