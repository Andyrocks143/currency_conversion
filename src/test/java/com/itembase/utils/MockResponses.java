package com.itembase.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.itembase.core.model.CurrencyConverter;
import com.itembase.core.model.ExchangeRate;

import java.util.HashMap;

public interface MockResponses {

    static ObjectMapper mapper = new ObjectMapper();

    static String exchangeRateString = "{\"base\":\"EUR\",\"date\":\"2020-07-26\",\"rates\":{\"CHF\":\"1.073303\",\"HRK\":\"7.516377\",\"FJD\":\"2.476809\",\"MXN\":\"26.057063\",\"GTQ\":\"8.924211\",\"CLP\":\"892.402029\",\"ZAR\":\"19.372106\",\"AUD\":\"1.636372\",\"ILS\":\"3.967984\",\"BSD\":\"1.160356\",\"IDR\":\"17105.467991\",\"TRY\":\"7.949813\",\"AED\":\"4.260913\",\"HKD\":\"9.005311\",\"TWD\":\"34.184842\",\"EUR\":\"1\",\"DOP\":\"67.184151\",\"DKK\":\"7.443028\",\"CAD\":\"1.558581\",\"MYR\":\"4.945224\",\"BGN\":\"1.955841\",\"NOK\":\"10.681826\",\"RON\":\"4.832344\",\"UYU\":\"49.019273\",\"CZK\":\"26.281902\",\"PKR\":\"193.941176\",\"SEK\":\"10.282858\",\"UAH\":\"32.104994\",\"ARS\":\"83.351124\",\"KZT\":\"478.596774\",\"SAR\":\"4.354027\",\"MVR\":\"17.886995\",\"INR\":\"86.826118\",\"CNY\":\"8.139534\",\"THB\":\"36.793997\",\"KRW\":\"1395.025647\",\"JPY\":\"123.608665\",\"PLN\":\"4.406568\",\"GBP\":\"0.910475\",\"HUF\":\"346.889142\",\"PHP\":\"57.301012\",\"RUB\":\"83.072034\",\"PYG\":\"8092.636364\",\"ISK\":\"157.743787\",\"COP\":\"4239\",\"USD\":\"1.162248\",\"EGP\":\"18.522472\",\"PAB\":\"1.160356\",\"SGD\":\"1.607665\",\"PEN\":\"4.096974\",\"NZD\":\"1.750767\",\"BRL\":\"6.051727\"},\"time_last_updated\":1595721847}";

    static ExchangeRate getMockExchangeRate() {

        HashMap<String, String> mockRates = new HashMap<>();
        mockRates.put("EUR", "1");
        mockRates.put("USD", "1.162248");
        mockRates.put("AED", "4.260913");
        mockRates.put("CAD", "1.558581");

        return new ExchangeRate("mock base",
                "mock date",
                1595721843,
                mockRates);
    }

    static CurrencyConverter getMockCurrencyConverter() {
        return new CurrencyConverter("mockFrom",
                "mockTo",
                Float.parseFloat("1.1223"),
                Float.parseFloat("2.3345"));
    }

    static String requestCurrencyConverter = "{\n" +
            "  \"from\": \"EUR\",\n" +
            "  \"to\": \"USD\",\n" +
            "  \"amount\": 80,\n" +
            "  \"converted\": 92.864\n" +
            "}";

}
