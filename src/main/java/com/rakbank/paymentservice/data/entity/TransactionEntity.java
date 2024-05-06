package com.rakbank.paymentservice.data.entity;


import com.rakbank.paymentservice.data.enums.Currency;
import com.rakbank.paymentservice.data.enums.PaymentMethodType;
import com.rakbank.paymentservice.data.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "student_id")
    private String studentId;

    @Column(name = "purchase_id")
    private String purchaseId;

    @Column(name = "transaction_reference")
    private String transactionReference;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_status")
    private TransactionStatus status;

    @Column(name ="amount")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name ="currency")
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name ="payment_method")
    private PaymentMethodType paymentMethod;

    @Column(name ="transaction_date")
    private ZonedDateTime transactionDate;

    @Column(name ="callback_date")
    private ZonedDateTime callbackDate;

}
