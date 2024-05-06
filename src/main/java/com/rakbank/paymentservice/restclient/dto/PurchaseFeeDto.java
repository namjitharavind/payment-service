package com.rakbank.paymentservice.restclient.dto;


import com.rakbank.paymentservice.data.enums.Currency;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class PurchaseFeeDto {

    private Long id;
    private Long feeId;
    private String feeName;
    private Double feeAmount;
    private Currency feeCurrency;
}
