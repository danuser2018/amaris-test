package me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.jetbrains.annotations.NotNull;

public interface PriceMapper {

    @NotNull
    PricesResponse toPricesResponse(@NotNull Price price);

}
