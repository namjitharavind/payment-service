package com.rakbank.paymentservice.service;

import com.rakbank.paymentservice.data.enums.PaymentMethodType;

import java.util.Optional;

public interface PaymentMethodServiceFactory {

     Optional<PaymentMethodService> getPaymentMethodService(PaymentMethodType paymentMethod);
}
