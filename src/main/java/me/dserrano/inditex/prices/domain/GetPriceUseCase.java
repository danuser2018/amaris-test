package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.primary.GetPricePort;
import me.dserrano.inditex.prices.domain.ports.secondary.LocatePricePort;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class GetPriceUseCase implements GetPricePort {

    private final LocatePricePort locatePricePort;

    @Autowired
    public GetPriceUseCase(LocatePricePort locatePricePort) {
        this.locatePricePort = locatePricePort;
    }

    @Override
    @NotNull
    public Price get(@NotNull LocalDateTime date, @NotNull String productId, @NotNull String brandId) {
        return locatePricePort.get(date, productId, brandId);
    }
}
