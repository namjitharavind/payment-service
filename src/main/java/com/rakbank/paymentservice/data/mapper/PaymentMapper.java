package com.rakbank.paymentservice.data.mapper;


import com.rakbank.paymentservice.data.dto.*;
import com.rakbank.paymentservice.data.entity.ApplePayEntity;
import com.rakbank.paymentservice.data.entity.CreditCardEntity;
import com.rakbank.paymentservice.data.entity.GooglePayEntity;
import com.rakbank.paymentservice.data.entity.TransactionEntity;
import com.rakbank.paymentservice.data.enums.TransactionStatus;
import com.rakbank.paymentservice.restclient.dto.PurchaseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.time.ZonedDateTime;
import java.util.UUID;

@Mapper(config = CommonMapperConfig.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,imports = {UUID.class, ZonedDateTime.class})
public interface PaymentMapper {
    PaymentMapper MAPPER = Mappers.getMapper(PaymentMapper.class);

    @Mapping(source = "paymentRequestSource.studentId", target = "studentId")
    @Mapping(source = "paymentRequestSource.purchaseId", target = "purchaseId")
    @Mapping(source = "transactionStatusSource", target = "status")
    @Mapping(source = "paymentRequestSource.paymentMethod", target = "paymentMethod")
    @Mapping(source = "purchaseSource.currency", target = "currency")
    @Mapping(source = "purchaseSource.customAmount", target = "amount")
    @Mapping(target = "transactionDate", expression = "java(ZonedDateTime.now())")
    TransactionEntity mapToTransactionEntity(PaymentRequest paymentRequestSource, PurchaseDto purchaseSource,TransactionStatus transactionStatusSource);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "creditCard.number", target = "number")
    @Mapping(source = "creditCard.expiry", target = "expiry")
    @Mapping(source = "creditCard.holderName", target = "holderName")
    @Mapping(source = "creditCard.type", target = "type")
    CreditCardEntity mapToCreditCardEntity(CreditCard creditCard);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "creditCardEntity.number", target = "number")
    @Mapping(source = "creditCardEntity.expiry", target = "expiry")
    @Mapping(source = "creditCardEntity.holderName", target = "holderName")
    @Mapping(source = "creditCardEntity.type", target = "type")
    CreditCard mapToCreditCard(CreditCardEntity creditCardEntity);



    ApplePayEntity mapToApplePayEntity(ApplePay applePay);

    GooglePayEntity mapToGooglePayEntity(GooglePay googlePay);


    @Mapping(source = "transactionEntity.id", target = "id")
    @Mapping(source = "transactionEntity.studentId", target = "studentId")
    @Mapping(source = "transactionEntity.purchaseId", target = "purchaseId")
    @Mapping(source = "transactionEntity.transactionReference", target = "transactionReference")
    @Mapping(source = "transactionEntity.status", target = "status")
    @Mapping(source = "transactionEntity.amount", target = "amount")
    @Mapping(source = "transactionEntity.currency", target = "currency")
    @Mapping(source = "transactionEntity.paymentMethod", target = "paymentMethod")
    @Mapping(source = "transactionEntity.transactionDate", target = "transactionDate")
    @Mapping(source = "transactionEntity.callbackDate", target = "callbackDate")
    @Mapping(source = "creditCardEntity", target = "creditCard")
    PaymentResponse mapToPaymentResponseCreditCard(TransactionEntity transactionEntity,CreditCardEntity creditCardEntity);

    @Mapping(source = "transactionEntity.id", target = "id")
    @Mapping(source = "transactionEntity.studentId", target = "studentId")
    @Mapping(source = "transactionEntity.purchaseId", target = "purchaseId")
    @Mapping(source = "transactionEntity.transactionReference", target = "transactionReference")
    @Mapping(source = "transactionEntity.status", target = "status")
    @Mapping(source = "transactionEntity.amount", target = "amount")
    @Mapping(source = "transactionEntity.paymentMethod", target = "paymentMethod")
    @Mapping(source = "transactionEntity.transactionDate", target = "transactionDate")
    @Mapping(source = "transactionEntity.callbackDate", target = "callbackDate")
    @Mapping(source = "applePayEntity", target = "applePay")
    PaymentResponse mapToPaymentResponseApplePay(TransactionEntity transactionEntity,ApplePayEntity applePayEntity);

    @Mapping(source = "transactionEntity.id", target = "id")
    @Mapping(source = "transactionEntity.studentId", target = "studentId")
    @Mapping(source = "transactionEntity.purchaseId", target = "purchaseId")
    @Mapping(source = "transactionEntity.transactionReference", target = "transactionReference")
    @Mapping(source = "transactionEntity.status", target = "status")
    @Mapping(source = "transactionEntity.amount", target = "amount")
    @Mapping(source = "transactionEntity.paymentMethod", target = "paymentMethod")
    @Mapping(source = "transactionEntity.transactionDate", target = "transactionDate")
    @Mapping(source = "transactionEntity.callbackDate", target = "callbackDate")
    @Mapping(source = "googlePayEntity", target = "googlePay")
    PaymentResponse mapToPaymentResponseGooglePay(TransactionEntity transactionEntity,GooglePayEntity googlePayEntity);



}
