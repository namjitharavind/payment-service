package com.rakbank.paymentservice.data.entity;


import com.rakbank.paymentservice.data.enums.CreditCardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="credit_card")
public class CreditCardEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String number;
    @Column(name = "expiry")
    private String expiry;
    @Column(name = "cvv")
    private String cvv;
    @Column(name = "holder_name")
    private String holderName;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private CreditCardType type;

    @OneToOne
    @MapsId
    @JoinColumn(name = "transaction_id")
    private TransactionEntity transaction;


}
