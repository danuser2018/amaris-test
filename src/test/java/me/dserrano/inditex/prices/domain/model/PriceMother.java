package me.dserrano.inditex.prices.domain.model;

import me.dserrano.inditex.prices.infrastructure.outbound.mongo.model.PriceDocument;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

    public static final Price PRICE_3 = Price.builder()
            .productId("35455")
            .brandId("1")
            .priceList("3")
            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
            .value(new BigDecimal("35.50"))
            .currency("EUR")
            .priority(1)
            .build();

    public static final Price PRICE_4 = Price.builder()
            .productId("35455")
            .brandId("1")
            .priceList("4")
            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
            .value(new BigDecimal("38.95"))
            .currency("EUR")
            .priority(1)
            .build();
}
