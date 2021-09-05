package me.dserrano.inditex.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceMother {

    public static final Price PRICE = Price.builder()
            .productId("35455")
            .brandId("1")
            .priceList("1")
            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
            .value(new BigDecimal("35.50"))
            .currency("EUR")
            .build();

}
