package me.dserrano.inditex.prices.domain;

import me.dserrano.inditex.prices.domain.model.Price;
import me.dserrano.inditex.prices.domain.ports.secondary.LocatePricePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetPriceUseCaseTest {

    private GetPriceUseCase classToTest;
    private LocatePricePort locatePricePort;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";


    @BeforeEach
    private void setUp(@Mock LocatePricePort locatePricePort) {
        this.locatePricePort = locatePricePort;
        this.classToTest = new GetPriceUseCase(locatePricePort);
        when(locatePricePort.get(date, productId, brandId)).thenReturn(PRICE);
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
    @DisplayName("Given a valid date, productId and brandId then secondary port is invoked")
    public void validRequestInvokeSecondaryPort() {
        // When
        classToTest.get(date, productId, brandId);

        // Then
        verify(locatePricePort).get(date, productId, brandId);
    }

}
