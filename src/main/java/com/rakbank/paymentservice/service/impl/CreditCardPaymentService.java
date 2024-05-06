package com.rakbank.paymentservice.service.impl;


import com.rakbank.paymentservice.data.dto.PaymentRequest;
import com.rakbank.paymentservice.data.dto.PaymentResponse;
import com.rakbank.paymentservice.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreditCardPaymentService implements PaymentMethodService {



    @Override
    public PaymentResponse pay(PaymentRequest request) {
        return null;
    }
}
