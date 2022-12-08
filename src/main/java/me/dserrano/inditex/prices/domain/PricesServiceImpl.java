package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.primary.PricesService;
import me.dserrano.inditex.prices.domain.ports.secondary.PricesDao;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.math.MathFlux;

import java.time.LocalDateTime;
import java.util.Comparator;

@Service
public class PricesServiceImpl implements PricesService {

    private final PricesDao pricesDao;
    private final Comparator<Price> priorityComparator;

    @Autowired
    public PricesServiceImpl(PricesDao pricesDao, Comparator<Price> priorityComparator) {
        this.pricesDao = pricesDao;
        this.priorityComparator = priorityComparator;

    }

    @Override
    @NotNull
    public Mono<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {
        return MathFlux.max(pricesDao.getPricesBy(date, productId, brandId), priorityComparator);
    }
}
