package com.rakbank.paymentservice.data.mapper;

import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;

@MapperConfig(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommonMapperConfig {
}
