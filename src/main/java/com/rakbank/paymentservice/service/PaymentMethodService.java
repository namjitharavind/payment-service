package com.rakbank.paymentservice.service;


import com.rakbank.paymentservice.data.dto.PaymentRequest;
import com.rakbank.paymentservice.data.dto.PaymentResponse;

public interface PaymentMethodService {

    PaymentResponse pay(PaymentRequest request);
}
