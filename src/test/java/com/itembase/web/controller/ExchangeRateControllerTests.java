package com.itembase.web.controller;

import com.itembase.core.exception.ExchangeRateNotFoundException;
import com.itembase.core.service.ExchangeRateService;
import com.itembase.utils.MockResponses;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExchangeRateController.class)
public class ExchangeRateControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ExchangeRateService exchangeRateService;

    @Test
    public void getExchangeRate_returns405() throws Exception {
        when(exchangeRateService.getExchangeRate()).thenThrow(ExchangeRateNotFoundException.class);

        mockMvc.perform(post("/utils/getexchangerate"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void getExchangeRate_returns200() throws Exception {
        when(exchangeRateService.getExchangeRate()).thenReturn(MockResponses.getMockExchangeRate());

        mockMvc.perform(post("/utils/getexchangerate"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful());
    }
}
