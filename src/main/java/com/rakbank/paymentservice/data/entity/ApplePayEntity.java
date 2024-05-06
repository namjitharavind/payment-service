package com.rakbank.paymentservice.data.entity;

import com.rakbank.paymentservice.data.enums.Currency;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="apple_pay")
public class ApplePayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @OneToOne
    @MapsId
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;

}
