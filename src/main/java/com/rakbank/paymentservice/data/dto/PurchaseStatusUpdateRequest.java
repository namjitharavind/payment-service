package com.rakbank.paymentservice.data.dto;

import com.rakbank.paymentservice.data.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class PurchaseStatusUpdateRequest {
    private PurchaseStatus status;
    private ZonedDateTime paidDate;
}
