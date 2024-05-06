package com.rakbank.paymentservice.restclient.dto;



import com.rakbank.paymentservice.data.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;


@AllArgsConstructor
@Getter
@Setter
public class TransactionStatusResponse {

    private TransactionStatus transactionStatus;
    private ZonedDateTime transactionDate;
}
