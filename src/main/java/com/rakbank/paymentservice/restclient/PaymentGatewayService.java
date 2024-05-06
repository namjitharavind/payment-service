package com.rakbank.paymentservice.restclient;

import com.rakbank.paymentservice.data.dto.TransactionRequest;
import com.rakbank.paymentservice.restclient.dto.TransactionResponse;
import com.rakbank.paymentservice.restclient.dto.TransactionStatusResponse;

import java.util.Optional;

public interface PaymentGatewayService {
    Optional<TransactionStatusResponse> getPaymentGatewayStatus(String id);
    TransactionResponse pay(TransactionRequest request);
}
