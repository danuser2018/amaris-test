package me.dserrano.inditex.prices.domain.ports.primary;

import me.dserrano.inditex.prices.domain.model.Price;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;

public interface GetPricePort {

    @NotNull
    Optional<Price> get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId);

}
