package com.rakbank.paymentservice.data.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="google_pay")
public class GooglePayEntity {

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
