package com.rakbank.paymentservice.restclient;


import com.rakbank.paymentservice.data.dto.PurchaseStatusUpdateRequest;
import com.rakbank.paymentservice.restclient.dto.PurchaseDto;

import java.util.Optional;

public interface PurchaseService {
    Optional<PurchaseDto> getPurchase(String id);
    void updatePurchaseStatus(PurchaseStatusUpdateRequest request,String purchaseId);
}
