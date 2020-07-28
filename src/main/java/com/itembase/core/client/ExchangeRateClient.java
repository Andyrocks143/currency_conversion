package com.itembase.core.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itembase.core.exception.ExchangeRateNotFoundException;
import com.itembase.core.model.ExchangeRate;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Random;

@Component
@Slf4j
public class ExchangeRateClient {

    @Autowired
    @Qualifier("exchangeRateRestTemplate")
    RestTemplate restTemplate;

    @Autowired
    ObjectMapper mapper;

    @Value("${itembase.api.exchangerate.url}")
    private String exchangeRateUrl;

    @Value("${itembase.api.exchangerate.updatetimeproperty.url}")
    private String exchangeRateUpdateTimeUrl;

    public ExchangeRate getExchangeRate() {

        ExchangeRate exchangeRate = getExchangeRateFromRandomSource();
        return exchangeRate;
    }

    private ExchangeRate getExchangeRateFromRandomSource() {
        Random myRand = new Random();
        if (myRand.nextBoolean()) {
            try {
                return getExchangeRateWithUpdateTime();
            } catch (Exception exception) {
                return getExchangeRateWithoutTime();
            }
        } else {
            try {
                return getExchangeRateWithoutTime();
            } catch (Exception exception) {
                return getExchangeRateWithUpdateTime();
            }
        }
    }

    private ExchangeRate getExchangeRateWithUpdateTime() {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHttpHeaders());

            log.info("HttpClient call to get Exchange Rate with updatetime. url: [{}]",
                    exchangeRateUpdateTimeUrl);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    exchangeRateUpdateTimeUrl,
                    HttpMethod.GET,
                    entity,
                    String.class);

            log.debug("Get EmployeeInfo for workerId status={}, Request={}",
                    responseEntity.getStatusCode(),
                    exchangeRateUpdateTimeUrl);

            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                log.error("getExchangeRateWithUpdateTime event could not be completed, Details: {}",
                        responseEntity.getBody());
                throw new ExchangeRateNotFoundException("Exchange rate with update time could not be found");
            }

            return mapper.readValue(responseEntity.getBody(), ExchangeRate.class);
        } catch (Exception exception) {
            log.error("Exception getting exchange rate with update time, Details: {}",
                    exception.getMessage());
            throw new ExchangeRateNotFoundException("Exchange rate with update time could not be found");
        }
    }

    private ExchangeRate getExchangeRateWithoutTime() {
        try {
            HttpEntity<?> entity = new HttpEntity<>(createHttpHeaders());

            log.info("HttpClient call to get Exchange Rate. url: [{}]",
                    exchangeRateUrl);

            ResponseEntity<String> responseEntity = restTemplate.exchange(
                    exchangeRateUrl,
                    HttpMethod.GET,
                    entity,
                    String.class);

            log.debug("Get EmployeeInfo for workerId status={}, Request={}",
                    responseEntity.getStatusCode(),
                    exchangeRateUrl);

            if (responseEntity.getStatusCode() != HttpStatus.OK) {
                log.error("getExchangeRateWithUpdateTime event could not be completed, Details: {}",
                        responseEntity.getBody());
                throw new ExchangeRateNotFoundException("Exchange rate could not be found");
            }

            return mapper.readValue(responseEntity.getBody(), ExchangeRate.class);
        } catch (Exception exception) {
            log.error("Exception getting exchange rate with update time, Details: {}",
                    exception.getMessage());
            throw new ExchangeRateNotFoundException("Exchange rate with update time could not be found");
        }
    }

    private HttpHeaders createHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }


    //In case basic Authentication is required
    private HttpHeaders createHttpHeadersWithBasicAuth(String user, String password) {
        String combineUserAndPwd = user + ":" + password;

        String encoding = java.util.Base64.getEncoder()
                .encodeToString(combineUserAndPwd
                        .getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.AUTHORIZATION, "Basic " + encoding);
        return headers;
    }


}
