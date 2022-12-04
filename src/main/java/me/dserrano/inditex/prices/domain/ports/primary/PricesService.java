package me.dserrano.inditex.prices.domain.ports.primary;

import me.dserrano.inditex.prices.domain.model.Price;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PricesService {
    @NotNull
    Optional<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId);
}
