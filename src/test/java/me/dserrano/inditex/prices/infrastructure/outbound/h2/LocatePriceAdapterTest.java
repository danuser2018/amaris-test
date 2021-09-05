package me.dserrano.inditex.prices.infrastructure.outbound.h2;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.infrastructure.outbound.h2.repository.PricesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE;
import static me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntityMother.PRICE_ENTITY_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocatePriceAdapterTest {

    private LocatePriceAdapter classToTest;
    private PricesRepository pricesRepository;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @BeforeEach
    private void setUp(@Mock PricesRepository pricesRepository) {
        this.pricesRepository = pricesRepository;
        this.classToTest = new LocatePriceAdapter(pricesRepository);
        when(pricesRepository.getPricesBy(date, productId, brandId)).thenReturn(List.of(PRICE_ENTITY_1));
    }

    @Test
    @DisplayName("Given a date, productId and brandId that produces a result then a Price is returned")
    public void validParametersReturnsAPrice() {
        // When
        Price result = classToTest.get(date, productId, brandId);

        // Then
        assertEquals(PRICE, result);
    }

    @Test
    @DisplayName("Given a date, productId and brandId that produces a result then a Price is returned")
    public void validParametersInvokeRepository() {
        // When
        classToTest.get(date, productId, brandId);

        // Then
        verify(pricesRepository).getPricesBy(date, productId, brandId);
    }
}
