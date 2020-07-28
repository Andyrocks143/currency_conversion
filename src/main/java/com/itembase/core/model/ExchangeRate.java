package com.itembase.core.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExchangeRate {

    private String base;
    private String date;

    @JsonProperty("time_last_updated")
    private long lastUpdateTime;

    @NotBlank(message = "Rates should not be blank")
    private HashMap<String, String> rates;
}
