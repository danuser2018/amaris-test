package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.ports.primary.GetPricePort;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static me.dserrano.inditex.prices.domain.model.PriceMother.PRICE_1;
import static me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponseMother.PRICES_RESPONSE_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
public class PricesControllerTest {
    @Mock
    private GetPricePort getPricePort;

    @Mock
    private PriceMapper priceMapper;

    @InjectMocks
    private PricesController classToTest;

    private final LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
    private final String productId = "35455";
    private final String brandId = "1";

    @Test
    @DisplayName("Given a valid request that produces a result then return an Ok response with a price")
    public void validRequestReturnsOKAndPrice() {
        // Given
        when(getPricePort.get(date, productId, brandId)).thenReturn(Optional.of(PRICE_1));
        when(priceMapper.toPricesResponse(PRICE_1)).thenReturn(PRICES_RESPONSE_1);

        // When
        ResponseEntity<PricesResponse> result = classToTest.getPrices(date, productId, brandId);

        // Then
        assertEquals(OK, result.getStatusCode());
        assertEquals(PRICES_RESPONSE_1, result.getBody());
    }

    @Test
    @DisplayName("Given a valid request then domain is invoke")
    public void validRequestInvokeDomain() {
        // Given
        when(getPricePort.get(date, productId, brandId)).thenReturn(Optional.of(PRICE_1));
        when(priceMapper.toPricesResponse(PRICE_1)).thenReturn(PRICES_RESPONSE_1);

        // When
        classToTest.getPrices(date, productId, brandId);

        // Then
        verify(getPricePort).get(date, productId, brandId);
    }

    @Test
    @DisplayName("Given a valid request that produces no price then return an empty Ok response")
    public void validRequestReturnsNoPrice() {
        // Given
        when(getPricePort.get(date, productId, brandId)).thenReturn(Optional.empty());

        // When
        ResponseEntity<PricesResponse> result = classToTest.getPrices(date, productId, brandId);

        // Then
        assertEquals(NO_CONTENT, result.getStatusCode());
        assertNull(result.getBody());
    }

}
