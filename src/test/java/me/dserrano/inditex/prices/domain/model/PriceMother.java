package me.dserrano.inditex.prices.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceMother {

    public static final Price PRICE_1 = Price.builder()
            .productId("35455")
            .brandId("1")
            .priceList("1")
            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
            .value(new BigDecimal("35.50"))
            .currency("EUR")
            .priority(0)
            .build();

    public static final Price PRICE_2 = Price.builder()
            .productId("35455")
            .brandId("1")
            .priceList("2")
            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
            .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
            .value(new BigDecimal("25.45"))
            .currency("EUR")
            .priority(1)
            .build();

}
