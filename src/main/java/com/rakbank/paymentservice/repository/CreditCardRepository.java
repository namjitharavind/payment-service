package com.rakbank.paymentservice.repository;


import com.rakbank.paymentservice.data.entity.CreditCardEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CreditCardRepository extends JpaRepository<CreditCardEntity, Long> {

    CreditCardEntity findByTransactionId(String transaction_id);
}