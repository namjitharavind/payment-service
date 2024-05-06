package com.rakbank.paymentservice.restclient.impl;


import com.rakbank.paymentservice.core.exception.BusinessException;
import com.rakbank.paymentservice.data.dto.PurchaseStatusUpdateRequest;
import com.rakbank.paymentservice.restclient.PurchaseService;
import com.rakbank.paymentservice.restclient.dto.PurchaseDto;
import com.rakbank.paymentservice.restclient.properties.PurchaseServiceProperties;
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
public class PurchaseServiceImpl implements PurchaseService {

    private static final String SERVICE_NAME = "purchase-service";

    @Qualifier("purchase-service-rest-client")
    private final RestClient restClient;

    private final PurchaseServiceProperties properties;

    @Retry(name = SERVICE_NAME, fallbackMethod = "getPurchaseFromCache")
    @Override
    public Optional<PurchaseDto> getPurchase(String id) {
        log.info("Calling Purchase Api  : {}/{}", properties.getPurchaseApi(), id);
        PurchaseDto result = restClient.get()
                .uri(properties.getPurchaseApi(), id)
                .accept(APPLICATION_JSON)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (request, response) -> {
                    throw new BusinessException("4001", HttpStatus.BAD_REQUEST, "Purchase Not Found");
                })
                .body(PurchaseDto.class);
        log.info("Purchase Api call result: {}", result);
        return Optional.ofNullable(result);
    }

    @Retry(name = SERVICE_NAME, fallbackMethod = "updatePurchaseStatusFallBack")
    @Override
    public void updatePurchaseStatus(PurchaseStatusUpdateRequest request, String purchaseId) {
        log.info("Calling Purchase Service for payment status update: {}", properties.getPurchaseStatusUpdateApi());
        ResponseEntity<Void> response = restClient.post()
                .uri(properties.getPurchaseStatusUpdateApi(), purchaseId)
                .contentType(APPLICATION_JSON)
                .body(request)
                .retrieve()
                .toBodilessEntity();
        log.info("Purchase Service Api call result: {}", response);
    }

    private void updatePurchaseStatusFallBack(PurchaseStatusUpdateRequest request, String purchaseId, Exception e) {
        log.error("Pushed to a dead letter queue for later call back");
    }

    private Optional<PurchaseDto> getPurchaseFromCache(String id, Exception e) {
        return Optional.empty();
    }
}
