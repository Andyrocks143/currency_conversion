package com.itembase.config;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${itembase.api.http.connect.timeout}")
    private int magellanStoreHttpConnectTimeout;

    @Value("${itembase.api.http.response.timeout}")
    private int magellanStoreHttpResponseTimeout;

    @Bean(name = "exchangeRateRestTemplate")
    public RestTemplate exchangeRateRestTemplate() {
        return getRestTemplate(magellanStoreHttpConnectTimeout, magellanStoreHttpResponseTimeout);
    }

    private RestTemplate getRestTemplate(int connectTimeout, int responseTimeout) {

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connectTimeout)
                .setSocketTimeout(responseTimeout)
                .build();

        CloseableHttpClient closeableHttpClient = HttpClientBuilder
                .create()
                .setDefaultRequestConfig(requestConfig)
                .build();

        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(closeableHttpClient));
    }
}
