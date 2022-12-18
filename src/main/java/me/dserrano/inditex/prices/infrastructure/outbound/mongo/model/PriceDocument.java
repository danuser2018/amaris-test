package me.dserrano.inditex.prices.infrastructure.outbound.mongo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "prices")
public class PriceDocument {
    @Id
    private String id;
    private String productId;
    private String brandId;
    private String priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BigDecimal value;
    private String currency;
    private Integer priority;
}
