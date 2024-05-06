package com.rakbank.paymentservice.service.impl;

import com.rakbank.paymentservice.data.enums.PaymentMethodType;
import com.rakbank.paymentservice.service.PaymentMethodService;
import com.rakbank.paymentservice.service.PaymentMethodServiceFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentMethodServiceFactoryImpl implements PaymentMethodServiceFactory {

    private final CreditCardPaymentService creditCardPaymentService;
    private final ApplePayPaymentService applePayPaymentService;
    private final GooglePayPaymentService googlePayPaymentService;

    public Optional<PaymentMethodService> getPaymentMethodService(PaymentMethodType paymentMethod) {

        if (paymentMethod.equals(PaymentMethodType.valueOf("CREDIT_DEBIT_CARD"))) {
            return Optional.ofNullable(creditCardPaymentService);
        } else if (paymentMethod.equals(PaymentMethodType.valueOf("APPLE_PAY"))) {
            return Optional.ofNullable(applePayPaymentService);
        } else if (paymentMethod.equals(PaymentMethodType.valueOf("GOOGLE_PAY"))) {
            return Optional.ofNullable(googlePayPaymentService);
        }
        return Optional.empty();
    }
}
