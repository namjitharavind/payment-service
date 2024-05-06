package com.rakbank.paymentservice.restclient.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rest.api.student.service")
public class StudentServiceProperties {
    private String name;
    private String baseUrl;
    private String studentByIdApi;
}
