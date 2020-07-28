package com.itembase.core.model;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrencyConverter {

    @NotBlank(message = "From Currency cannot be blank")
    private String from;

    @NotBlank(message = "To Currency cannot be blank")
    private String to;

    @NotBlank(message = "amount cannot be blank")
    private float amount;

    private float converted;

    public CurrencyConverter(@NotBlank(message = "From Currency cannot be blank") String from,
                             @NotBlank(message = "To Currency cannot be blank") String to,
                             @NotBlank(message = "amount cannot be blank") float amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }
}
