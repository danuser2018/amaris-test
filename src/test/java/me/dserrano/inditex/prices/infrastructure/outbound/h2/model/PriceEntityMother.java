package me.dserrano.inditex.prices.infrastructure.outbound.h2.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PriceEntityMother {

    public static final PriceEntity PRICE_ENTITY_1 = PriceEntity.builder()
            .id(1L)
            .productId("35455")
            .brandId("1")
            .priceList("1")
            .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
            .price(new BigDecimal("35.50"))
            .currency("EUR")
            .priority(0)
            .build();

    public static final PriceEntity PRICE_ENTITY_2 = PriceEntity.builder()
            .id(2L)
            .productId("35455")
            .brandId("1")
            .priceList("2")
            .startDate(LocalDateTime.of(2020, 6, 14, 15, 0, 0))
            .endDate(LocalDateTime.of(2020, 6, 14, 18, 30, 0))
            .price(new BigDecimal("25.45"))
            .currency("EUR")
            .priority(1)
            .build();

    public static final PriceEntity PRICE_ENTITY_3 = PriceEntity.builder()
            .id(3L)
            .productId("35455")
            .brandId("1")
            .priceList("3")
            .startDate(LocalDateTime.of(2020, 6, 15, 0, 0, 0))
            .endDate(LocalDateTime.of(2020, 6, 15, 11, 0, 0))
            .price(new BigDecimal("35.50"))
            .currency("EUR")
            .priority(1)
            .build();

    public static final PriceEntity PRICE_ENTITY_4 = PriceEntity.builder()
            .id(4L)
            .productId("35455")
            .brandId("1")
            .priceList("4")
            .startDate(LocalDateTime.of(2020, 6, 15, 16, 0, 0))
            .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
            .price(new BigDecimal("38.95"))
            .currency("EUR")
            .priority(1)
            .build();

}
