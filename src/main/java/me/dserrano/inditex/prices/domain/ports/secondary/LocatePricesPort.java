package me.dserrano.inditex.prices.domain.ports.secondary;

import me.dserrano.inditex.prices.domain.model.Price;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.List;

public interface LocatePricesPort {
    @NotNull
    List<Price> get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId);

}
