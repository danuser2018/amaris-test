package me.dserrano.inditex.prices.infrastructure.inbound.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
public class PricesResponse {

    @JsonProperty("product-id") String productId;
    @JsonProperty("brand-id") String brandId;
    @JsonProperty("price-list") String priceList;
    @JsonProperty("start-date") LocalDateTime startDate;
    @JsonProperty("end-date") LocalDateTime endDate;
    @JsonProperty("price") BigDecimal price;
    @JsonProperty("currency") String currency;

}
