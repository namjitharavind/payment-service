package com.rakbank.paymentservice.repository;



import com.rakbank.paymentservice.data.dto.PaymentResponse;
import com.rakbank.paymentservice.data.entity.TransactionEntity;
import com.rakbank.paymentservice.data.enums.TransactionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.ZonedDateTime;


public interface TransactionRepository extends JpaRepository<TransactionEntity, String> {

    @Transactional
    @Modifying
    @Query("UPDATE TransactionEntity t SET t.status = :status , t.callbackDate = :callbackDate where t.id = :transactionId")
    void updateTransactionStatus(@Param("status") TransactionStatus status, @Param("callbackDate") ZonedDateTime callbackDate, @Param("transactionId")  String transactionId);

    @Transactional
    @Modifying
    @Query("UPDATE TransactionEntity t SET t.status = :status , t.transactionReference = :transactionReference where t.id = :transactionId")
    void updateTransactionStatusAndRef(@Param("status") TransactionStatus status, @Param("transactionReference") String transactionReference, @Param("transactionId")  String transactionId);
}