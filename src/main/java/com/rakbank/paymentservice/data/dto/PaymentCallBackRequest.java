package com.rakbank.paymentservice.data.dto;


import com.rakbank.paymentservice.data.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PaymentCallBackRequest {
    private String transactionId;
    private String transactionReferenceId;
    private TransactionStatus transactionStatus;
}
