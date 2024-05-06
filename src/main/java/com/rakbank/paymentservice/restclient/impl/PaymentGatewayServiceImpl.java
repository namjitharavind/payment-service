package com.rakbank.paymentservice.restclient.impl;


import com.rakbank.paymentservice.core.exception.BusinessException;
import com.rakbank.paymentservice.data.dto.TransactionRequest;
import com.rakbank.paymentservice.restclient.PaymentGatewayService;
import com.rakbank.paymentservice.restclient.dto.TransactionResponse;
import com.rakbank.paymentservice.restclient.dto.TransactionStatusResponse;
import com.rakbank.paymentservice.restclient.properties.PaymentGateWayServiceProperties;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    private static final String SERVICE_NAME = "payment-service";

    @Qualifier("payment-gateway-service-rest-client")
    private final RestClient restClient;

    private final PaymentGateWayServiceProperties properties;


    @Retry(name = SERVICE_NAME, fallbackMethod = "GetTransactionStatusFallBack")
    @Override
    public Optional<TransactionStatusResponse> getPaymentGatewayStatus(String id) {

        log.info("Calling Payment gateway Api  : {}/{}", properties.getPaymentGatewayGetTransactionStatusApi(), id);
        TransactionStatusResponse result = restClient.get()
                .uri(properties.getPaymentGatewayGetTransactionStatusApi(), id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .body(TransactionStatusResponse.class);
        log.info("Purchase Api call result: {}", result);
        return Optional.ofNullable(result);
    }

    @Retry(name = SERVICE_NAME, fallbackMethod = "updateTransactionStatusFallBack")
    @Override
    public TransactionResponse pay(TransactionRequest request) {
          log.info("Calling Payment GateWay Service for payment status update: {}", properties.getPaymentGatewayTransactionApi());
        ResponseEntity<TransactionResponse> response = restClient.post()
                .uri(properties.getPaymentGatewayTransactionApi())
                .accept(APPLICATION_JSON)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toEntity(TransactionResponse.class);
        log.info("Payment GateWay Api call result: {}", response.getBody());
        log.info("Payment GateWay Api call result status code: {}", response.getStatusCode());
        return response.getBody();
    }

    public TransactionResponse updateTransactionStatusFallBack(TransactionRequest request,Exception e) {
        log.error("Transaction Request Retyr Fallback method called",e);
        log.info("================================================");
      return new TransactionResponse();
    }

    public Optional<TransactionResponse> getTransactionStatusFallBack(String id,Exception e) {
        return Optional.empty();
    }
}
