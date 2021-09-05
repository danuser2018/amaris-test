package me.dserrano.inditex.prices.infrastructure.outbound.h2.mapper;

import me.dserrano.inditex.prices.domain.model.Price;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE;
import static me.dserrano.inditex.prices.infrastructure.outbound.h2.model.PriceEntityMother.PRICE_ENTITY_1;

public class PriceEntityMapperImplTest {

    private PriceEntityMapperImpl priceEntityMapper = new PriceEntityMapperImpl();

    @Test
    @DisplayName("Given a valid PriceEntity then a Price is returned")
    public void testValidPriceEntity() {
        // When
        Price result = priceEntityMapper.toPrice(PRICE_ENTITY_1);

        // Then
        Assertions.assertEquals(PRICE, result);
    }

}
