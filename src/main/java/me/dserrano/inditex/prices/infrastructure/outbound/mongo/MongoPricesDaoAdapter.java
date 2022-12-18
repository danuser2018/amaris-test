package me.dserrano.inditex.prices.infrastructure.outbound.mongo;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.PricesDao;
import me.dserrano.inditex.prices.infrastructure.outbound.mongo.mapper.PriceDocumentMapper;
import me.dserrano.inditex.prices.infrastructure.outbound.mongo.repository.PriceDocumentRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Component
public class MongoPricesDaoAdapter implements PricesDao {

    private final PriceDocumentRepository priceDocumentRepository;
    private final PriceDocumentMapper priceDocumentMapper;

    @Autowired
    public MongoPricesDaoAdapter(PriceDocumentRepository priceDocumentRepository, PriceDocumentMapper priceDocumentMapper) {
        this.priceDocumentRepository = priceDocumentRepository;
        this.priceDocumentMapper = priceDocumentMapper;
    }

    @Override
    public @NotNull Flux<Price> getPricesBy(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {
        return priceDocumentRepository.findPricesBy(date, productId, brandId).map(priceDocumentMapper::toPrice);
    }
}
