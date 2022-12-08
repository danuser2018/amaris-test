package me.dserrano.inditex.prices.infrastructure.outbound.h2.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("PRICES")
public class PriceEntity {
    @Id
    @Column("PRICE_ID")
    private Long id;

    @Column("PRODUCT_ID")
    @NonNull
    private String productId;

    @Column("BRAND_ID")
    @NonNull
    private String brandId;

    @Column("PRICE_LIST")
    @NonNull
    private String priceList;

    @Column("START_DATE")
    @NonNull
    private LocalDateTime startDate;

    @Column("END_DATE")
    @NonNull
    private LocalDateTime endDate;

    @Column("PRICE")
    @NonNull
    private BigDecimal value;

    @Column("CURR")
    @NonNull
    private String currency;

    @Column("PRIORITY")
    @NonNull
    private Integer priority;
}
