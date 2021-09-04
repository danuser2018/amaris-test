package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPriceUseCaseTest {

    private final GetPriceUseCase classToTest = new GetPriceUseCase();

    @Test
    @DisplayName("Given a date, productId and brandId that produces a result then a Price is returned")
    public void validParametersReturnsAPrice() {
        // Given
        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        // When
        Price result = classToTest.get(date, productId, brandId);

        // Then
        assertEquals(PRICE, result);
    }

}
