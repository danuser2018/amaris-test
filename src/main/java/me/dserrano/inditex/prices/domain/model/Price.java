package me.dserrano.inditex.prices.domain.model;

import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class Price {

    String productId;
    String brandId;
    String priceList;
    LocalDateTime startDate;
    LocalDateTime endDate;
    BigDecimal value;
    String currency;
    int priority;

}
