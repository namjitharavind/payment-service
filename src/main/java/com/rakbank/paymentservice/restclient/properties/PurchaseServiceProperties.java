package com.rakbank.paymentservice.restclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rest.api.purchase.service")
public class PurchaseServiceProperties {
    private String name;
    private String baseUrl;
    private String purchaseApi;
    private String purchaseStatusUpdateApi;
}
