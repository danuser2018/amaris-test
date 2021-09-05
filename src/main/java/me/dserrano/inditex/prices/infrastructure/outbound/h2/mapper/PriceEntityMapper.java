package me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntity;
import org.jetbrains.annotations.NotNull;

public interface PriceEntityMapper {

    @NotNull
    Price toPrice(@NotNull PriceEntity priceEntity);

}
