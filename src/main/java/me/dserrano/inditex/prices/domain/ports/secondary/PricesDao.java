package me.dserrano.inditex.prices.domain.ports.secondary;

import me.dserrano.inditex.prices.domain.model.Price;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

public interface PricesDao {
    @NotNull
    Flux<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId);
}
