package com.rakbank.paymentservice.service;


import com.rakbank.paymentservice.data.dto.PaymentCallBackRequest;
import com.rakbank.paymentservice.data.dto.PaymentResponse;
import com.rakbank.paymentservice.data.dto.PaymentRequest;

import java.util.Optional;

public interface PaymentService {

    Optional<PaymentResponse> process(PaymentRequest request);
    Optional<PaymentResponse> findTransactionById(String id);
    void updateTransactionStatus(PaymentCallBackRequest request);
}
