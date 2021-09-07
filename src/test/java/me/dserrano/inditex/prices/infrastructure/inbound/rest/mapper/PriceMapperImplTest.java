package me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper;

import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceMapperImplTest {

    private final PriceMapperImpl classToTest = new PriceMapperImpl();

    @Test
    @DisplayName("Given a valid price, then a PricesResponse is returned")
    public void mapValidInstance() {
        // When
        PricesResponse result = classToTest.toPricesResponse(PRICE_1);

        // Then
        assertEquals(PRICE_1.getProductId(), result.getProductId());
        assertEquals(PRICE_1.getBrandId(), result.getBrandId());
        assertEquals(PRICE_1.getPriceList(), result.getPriceList());
        assertEquals(PRICE_1.getStartDate(), result.getStartDate());
        assertEquals(PRICE_1.getEndDate(), result.getEndDate());
        assertEquals(PRICE_1.getValue(), result.getPrice());
        assertEquals(PRICE_1.getCurrency(), result.getCurrency());
    }
}
