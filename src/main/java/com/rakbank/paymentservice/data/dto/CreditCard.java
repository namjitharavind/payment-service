package com.rakbank.paymentservice.data.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.rakbank.paymentservice.data.enums.CreditCardType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class CreditCard {
    private String id;
    private String number;
    private String expiry;
    private String cvv;
    private String holderName;
    private CreditCardType type;

}
