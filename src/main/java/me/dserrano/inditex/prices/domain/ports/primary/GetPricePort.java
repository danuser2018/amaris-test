package me.dserrano.inditex.prices.domain.ports.primary;

import me.dserrano.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPricePort {

    Price get(LocalDateTime date, String productId, String brandId);

}
