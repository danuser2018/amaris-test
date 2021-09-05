package me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PriceMapperImpl implements PriceMapper {

    @Override
    @NotNull
    public PricesResponse toPricesResponse(@NotNull Price price) {
        return PricesResponse.builder()
                .productId(price.getProductId())
                .brandId(price.getBrandId())
                .priceList(price.getPriceList())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price(price.getValue())
                .currency(price.getCurrency())
                .build();
    }
}
