package me.dserrano.inditex.prices.domain.ports.primary;

import me.dserrano.inditex.prices.domain.model.Price;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public interface GetPricePort {

    @NotNull
    Price get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId);

}
