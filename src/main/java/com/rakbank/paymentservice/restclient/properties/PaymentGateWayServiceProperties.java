package com.rakbank.paymentservice.restclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rest.api.payment.gateway.service")
public class PaymentGateWayServiceProperties {
    private String name;
    private String baseUrl;
    private String paymentGatewayTransactionApi;
    private String paymentGatewayGetTransactionStatusApi;
}
