package com.rakbank.paymentservice.data.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.rakbank.paymentservice.data.enums.Currency;
import com.rakbank.paymentservice.data.enums.PaymentMethodType;
import com.rakbank.paymentservice.data.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@JsonInclude(Include.NON_NULL)
@Getter
@Setter
public class PaymentResponse {

    private String id;
    private String studentId;
    private String purchaseId;
    private String transactionReference;
    private TransactionStatus status;
    private Double amount;
    private Currency currency;
    private PaymentMethodType paymentMethod;
    private ZonedDateTime transactionDate;
    private ZonedDateTime callbackDate;
    private CreditCard creditCard;
    private ApplePay applePay;
    private GooglePay googlePay;
}
