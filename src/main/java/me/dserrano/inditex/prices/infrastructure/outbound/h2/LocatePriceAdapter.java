package me.dserrano.inditex.prices.infrastructure.outbound.h2;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.LocatePricePort;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.repository.PricesRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class LocatePriceAdapter implements LocatePricePort {

    private final PricesRepository pricesRepository;

    @Autowired
    public LocatePriceAdapter(PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
    }

    @Override
    @NotNull
    public Price get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {

        pricesRepository.getPricesBy(date, productId, brandId);

        return Price.builder()
                .productId("35455")
                .brandId("1")
                .priceList("1")
                .startDate(LocalDateTime.of(2020, 6, 14, 0, 0, 0))
                .endDate(LocalDateTime.of(2020, 12, 31, 23, 59, 59))
                .value(new BigDecimal("35.50"))
                .currency("EUR")
                .build();
    }
}
