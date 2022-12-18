package me.dserrano.inditex.prices.infrastructure.outbound.mongo.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceDocumentMother {

    public static final PriceDocument PRICE_DOCUMENT_1 = PriceDocument.builder()
            .productId("35455")
            .brandId("1")
            .priceList("1")
            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
            .value(new BigDecimal("35.50"))
            .currency("EUR")
            .priority(0)
            .build();

    public static final PriceDocument PRICE_DOCUMENT_2 = PriceDocument.builder()
            .productId("35455")
            .brandId("1")
            .priceList("2")
            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
            .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
            .value(new BigDecimal("25.45"))
            .currency("EUR")
            .priority(1)
            .build();

    public static final PriceDocument PRICE_DOCUMENT_3 = PriceDocument.builder()
            .productId("35455")
            .brandId("1")
            .priceList("3")
            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
            .value(new BigDecimal("35.50"))
            .currency("EUR")
            .priority(1)
            .build();

    public static final PriceDocument PRICE_DOCUMENT_4 = PriceDocument.builder()
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
