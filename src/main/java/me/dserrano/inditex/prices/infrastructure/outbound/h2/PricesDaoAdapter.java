package me.dserrano.inditex.prices.infrastructure.outbound.h2;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.PricesDao;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper.PriceEntityMapper;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.repository.PriceEntityRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PricesDaoAdapter implements PricesDao {

    private final PriceEntityRepository priceEntityRepository;
    private final PriceEntityMapper priceEntityMapper;

    @Autowired
    public PricesDaoAdapter(PriceEntityRepository priceEntityRepository, PriceEntityMapper priceEntityMapper) {
        this.priceEntityRepository = priceEntityRepository;
        this.priceEntityMapper = priceEntityMapper;
    }

    @Override
    @NotNull
    public List<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {

        return priceEntityRepository.getPricesBy(date, productId, brandId)
                .stream()
                .map(priceEntityMapper::toPrice)
                .collect(Collectors.toList());

    }
}
