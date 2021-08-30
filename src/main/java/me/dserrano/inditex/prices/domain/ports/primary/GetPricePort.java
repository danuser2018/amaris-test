package me.dserrano.inditex.prices.domain.ports.primary;

import me.dserrano.inditex.prices.domain.model.Price;

import java.time.LocalDateTime;

public interface GetPrices {

    Price getPrices(LocalDateTime date, String productId, String brandId) {

    }

}
