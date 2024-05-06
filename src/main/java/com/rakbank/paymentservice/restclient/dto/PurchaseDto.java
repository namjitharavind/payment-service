package com.rakbank.paymentservice.restclient.dto;



import com.rakbank.paymentservice.data.enums.Currency;
import com.rakbank.paymentservice.data.enums.PurchaseStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.ZonedDateTime;

import java.util.List;

@ToString
@Getter
@Setter
public class PurchaseDto {
    private String id;
    private Long studentId;
    private String studentName;
    private String schoolName;
    private Double totalAmount;
    private Double customAmount;
    private Currency currency;
    private ZonedDateTime creationDate;
    private ZonedDateTime paidDate;
    private PurchaseStatus status;
    private List<PurchaseFeeDto> purchaseFees;
}
