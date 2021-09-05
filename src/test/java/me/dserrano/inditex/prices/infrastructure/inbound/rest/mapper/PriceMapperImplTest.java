package me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper;

import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PriceMapperImplTest {

    private final PriceMapperImpl classToTest = new PriceMapperImpl();

    @Test
    @DisplayName("Given a valid price, then a PricesResponse is returned")
    public void mapValidInstance() {
        // When
        PricesResponse result = classToTest.toPricesResponse(PRICE);

        // Then
        assertEquals(PRICE.getProductId(), result.getProductId());
        assertEquals(PRICE.getBrandId(), result.getBrandId());
        assertEquals(PRICE.getPriceList(), result.getPriceList());
        assertEquals(PRICE.getStartDate(), result.getStartDate());
        assertEquals(PRICE.getEndDate(), result.getEndDate());
        assertEquals(PRICE.getValue(), result.getPrice());
        assertEquals(PRICE.getCurrency(), result.getCurrency());
    }
}
