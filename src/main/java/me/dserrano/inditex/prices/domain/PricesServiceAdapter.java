package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.primary.PricesService;
import me.dserrano.inditex.prices.domain.ports.secondary.PricesDao;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class PricesServiceAdapter implements PricesService {

    private final PricesDao pricesDao;

    @Autowired
    public PricesServiceAdapter(PricesDao pricesDao) {
        this.pricesDao = pricesDao;
    }

    @Override
    @NotNull
    public Mono<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {
        return Flux.fromIterable(pricesDao.getPricesBy(date, productId, brandId))
                .reduce((p1, p2) -> Comparator.comparingInt(Price::getPriority).compare(p1, p2) <= 0 ? p1 : p2);
    }
}
