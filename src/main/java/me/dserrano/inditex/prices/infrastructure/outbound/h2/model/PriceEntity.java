package me.dserrano.inditex.prices.infrastructure.outbound.h2.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "PRICES")
public class PriceEntity {
    @Id
    @Column(name = "PRICE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "PRODUCT_ID", nullable = false)
    private String productId;

    @Column(name = "BRAND_ID", nullable = false)
    private String brandId;

    @Column(name = "PRICE_LIST", nullable = false)
    private String priceList;

    @Column(name = "START_DATE", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "END_DATE", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "CURR", nullable = false)
    private String currency;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;
}
