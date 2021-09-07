package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.primary.GetPricePort;
import me.dserrano.inditex.prices.domain.ports.secondary.LocatePricesPort;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

@Service
public class GetPriceUseCase implements GetPricePort {

    private final LocatePricesPort locatePricesPort;

    @Autowired
    public GetPriceUseCase(LocatePricesPort locatePricesPort) {
        this.locatePricesPort = locatePricesPort;
    }

    @Override
    @NotNull
    public Optional<Price> get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {

        return locatePricesPort.get(date, productId, brandId)
                .stream()
                .max(Comparator.comparingInt(Price::getPriority));
    }
}
