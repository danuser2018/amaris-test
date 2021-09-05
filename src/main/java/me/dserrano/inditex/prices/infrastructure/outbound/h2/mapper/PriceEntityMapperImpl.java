package me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PriceEntityMapperImpl implements PriceEntityMapper {
    @Override
    @NotNull
    public Price toPrice(@NotNull PriceEntity priceEntity) {
        return Price.builder()
                .productId(priceEntity.getProductId())
                .brandId(priceEntity.getBrandId())
                .priceList(priceEntity.getPriceList())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .value(priceEntity.getPrice())
                .currency(priceEntity.getCurrency())
                .build();
    }
}
