package me.dserrano.inditex.prices.infrastructure.outbound.h2;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.LocatePricesPort;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper.PriceEntityMapper;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.repository.PricesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocatePricesAdapter implements LocatePricesPort {

    private final PricesRepository pricesRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Autowired
    public LocatePricesAdapter(PricesRepository pricesRepository, PriceEntityMapper priceEntityMapper) {
        this.pricesRepository = pricesRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    @NotNull
    public List<Price> get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {

        return pricesRepository.getPricesBy(date, productId, brandId)
                .stream()
                .map(priceEntityMapper::toPrice)
                .collect(Collectors.toList());

    }
}
