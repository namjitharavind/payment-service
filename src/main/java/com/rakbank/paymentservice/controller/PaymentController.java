package com.rakbank.paymentservice.controller;


import com.rakbank.paymentservice.core.data.ErrorResponse;
import com.rakbank.paymentservice.core.exception.BusinessException;
import com.rakbank.paymentservice.data.dto.PaymentCallBackRequest;
import com.rakbank.paymentservice.data.dto.PaymentRequest;
import com.rakbank.paymentservice.data.dto.PaymentResponse;
import com.rakbank.paymentservice.service.PaymentService;
import io.micrometer.observation.annotation.Observed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;



import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static com.rakbank.paymentservice.core.constants.Errors.TRANSACTION_NOT_FOUND;


@RequiredArgsConstructor
@RestController
@RequestMapping("/payment")
@Tag(name="Payment")
public class PaymentController {

    private final PaymentService paymentService;


    @Operation(
            description = "Create a Student",
            tags = {"Payment"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    }
    )
    @PostMapping
    public Optional<PaymentResponse> pay(@RequestBody PaymentRequest request) {
        return paymentService.process(request);
    }

    @Operation(
            description = "Get Transaction by Id",
            tags = {"Payment"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "Student not found", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    }),
                    @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
                    })
            }
    )
    @GetMapping(path = "/{id}")
    public PaymentResponse getTransactionById(@PathVariable String id) {
        return paymentService.findTransactionById(id).orElseThrow(() -> new BusinessException(TRANSACTION_NOT_FOUND));
    }


    @Operation(
            description = "Transaction update callback",
            tags = {"Payment"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            }),
            @ApiResponse(responseCode = "500", description = "Unexpected error", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))
            })
    }
    )
    @PostMapping(path = "/status-update-callback")
    public String updateStatus(@RequestBody PaymentCallBackRequest request) {
            paymentService.updateTransactionStatus(request);
        return "SUCCESS";
    }
}
