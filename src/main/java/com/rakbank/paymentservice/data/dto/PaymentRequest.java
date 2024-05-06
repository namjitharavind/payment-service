package com.rakbank.paymentservice.data.dto;


import com.rakbank.paymentservice.data.enums.PaymentMethodType;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentRequest {

    private Long studentId;
    private String purchaseId;
    private PaymentMethodType paymentMethod;
    private CreditCard creditCard;
    private ApplePay applePay;
    private GooglePay googlePay;
}
