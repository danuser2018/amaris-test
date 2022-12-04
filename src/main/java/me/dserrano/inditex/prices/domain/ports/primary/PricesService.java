package me.dserrano.inditex.prices.domain.ports.primary;

import me.dserrano.inditex.prices.domain.model.Price;
import org.jetbrains.annotations.NotNull;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public interface PricesService {
    @NotNull
    Mono<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId);
}
