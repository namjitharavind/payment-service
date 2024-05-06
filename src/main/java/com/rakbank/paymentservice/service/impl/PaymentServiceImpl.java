package com.rakbank.paymentservice.service.impl;

import com.rakbank.paymentservice.core.exception.BusinessException;
import com.rakbank.paymentservice.data.dto.*;
import com.rakbank.paymentservice.data.entity.CreditCardEntity;
import com.rakbank.paymentservice.data.entity.TransactionEntity;
import com.rakbank.paymentservice.data.enums.PaymentMethodType;
import com.rakbank.paymentservice.data.enums.PurchaseStatus;
import com.rakbank.paymentservice.data.enums.TransactionStatus;
import com.rakbank.paymentservice.data.mapper.PaymentMapper;
import com.rakbank.paymentservice.repository.CreditCardRepository;
import com.rakbank.paymentservice.repository.TransactionRepository;
import com.rakbank.paymentservice.restclient.PaymentGatewayService;
import com.rakbank.paymentservice.restclient.PurchaseService;
import com.rakbank.paymentservice.restclient.StudentService;
import com.rakbank.paymentservice.restclient.dto.TransactionResponse;
import com.rakbank.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentGatewayService paymentGatewayService;
    private final PurchaseService purchaseService;
    private final StudentService studentService;
    private final TransactionRepository transactionRepository;
    private final CreditCardRepository creditCardRepository;
    private final PaymentMapper paymentMapper;

    @Override
    public Optional<PaymentResponse> process(PaymentRequest request) {

        return Optional.ofNullable(
                purchaseService.getPurchase(request.getPurchaseId())
                        .map(p -> studentService.getStudentsById(request.getStudentId()).map(s -> {
                            TransactionEntity transactionEntity = paymentMapper.mapToTransactionEntity(request, p, TransactionStatus.NOT_YET_STARTED);
                            TransactionEntity transactionResult = transactionRepository.saveAndFlush(transactionEntity);
                            if (request.getPaymentMethod().equals(PaymentMethodType.CREDIT_DEBIT_CARD)) {
                                CreditCardEntity creditCardEntity = paymentMapper.mapToCreditCardEntity(request.getCreditCard());
                                creditCardEntity.setTransaction(transactionResult);
                                CreditCardEntity creditCardResult = creditCardRepository.saveAndFlush(creditCardEntity);
                                TransactionRequest transactionRequest = new TransactionRequest();
                                transactionRequest.setTransactionId(transactionResult.getId());
                                transactionRequest.setCardNumber(creditCardResult.getNumber());
                                transactionRequest.setCardExpiry(creditCardResult.getExpiry());
                                transactionRequest.setCardCvv(creditCardResult.getCvv());
                                transactionRequest.setCardHolderName(creditCardResult.getHolderName());
                                transactionRequest.setType(creditCardResult.getType().toString());
                                TransactionResponse transactionResponse = paymentGatewayService.pay(transactionRequest);
                                transactionResult.setTransactionReference(transactionResponse.getTransactionReference());
                                transactionRepository.updateTransactionStatusAndRef(TransactionStatus.ACCEPTED, transactionResponse.getTransactionReference(), transactionResult.getId());
                                return paymentMapper.mapToPaymentResponseCreditCard(transactionResult, creditCardResult);
                            }
                            return null;
                        }).orElseThrow(() -> new BusinessException("4001", HttpStatus.NOT_FOUND, "Student not Found")))
                        .orElseThrow(() -> new BusinessException("4002", HttpStatus.NOT_FOUND, "Purchase not Found")));
    }

    @Override
    public Optional<PaymentResponse> findTransactionById(String id) {

        return Optional.ofNullable(paymentMapper.mapToPaymentResponseCreditCard(transactionRepository.findById(id).get(), creditCardRepository.findByTransactionId(id)));
    }

    @Async
    @Override
    public void updateTransactionStatus(PaymentCallBackRequest request) {
        transactionRepository.updateTransactionStatus(request.getTransactionStatus(), ZonedDateTime.now(), request.getTransactionId());
        PurchaseStatusUpdateRequest purchaseStatusUpdateRequest = new PurchaseStatusUpdateRequest();
        purchaseStatusUpdateRequest.setStatus(PurchaseStatus.PAID);
        purchaseStatusUpdateRequest.setPaidDate(ZonedDateTime.now());
        transactionRepository.findById(request.getTransactionId()).ifPresent(t -> {
            purchaseService.updatePurchaseStatus(purchaseStatusUpdateRequest, t.getPurchaseId());
        });

    }


}
