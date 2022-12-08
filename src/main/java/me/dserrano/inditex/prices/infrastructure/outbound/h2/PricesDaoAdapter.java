package me.dserrano.inditex.prices.infrastructure.outbound.h2;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.PricesDao;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper.PriceEntityMapper;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.repository.PriceEntityRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Scheduler;

import java.time.LocalDateTime;

@Component
public class PricesDaoAdapter implements PricesDao {

    private final PriceEntityRepository priceEntityRepository;
    private final PriceEntityMapper priceEntityMapper;
    private final Scheduler jdbcScheduler;

    @Autowired
    public PricesDaoAdapter(
            PriceEntityRepository priceEntityRepository,
            PriceEntityMapper priceEntityMapper,
            Scheduler jdbcScheduler) {
        this.priceEntityRepository = priceEntityRepository;
        this.priceEntityMapper = priceEntityMapper;
        this.jdbcScheduler = jdbcScheduler;
    }

    @Override
    @NotNull
    public Flux<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {
        return Flux.defer(() -> Flux.fromIterable(priceEntityRepository.getPricesBy(date, productId, brandId)))
                .subscribeOn(jdbcScheduler)
                .map(priceEntityMapper::toPrice);
    }
}
