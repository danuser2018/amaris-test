package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.model.PriceMother;
import me.dserrano.inditex.prices.domain.ports.primary.GetPricePort;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.model.PricesResponseMother;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(PricesController.class)
@ExtendWith(MockitoExtension.class)
public class PricesControllerIT {

    @MockBean
    private GetPricePort getPricePort;

    @MockBean
    private PriceMapper priceMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid date, product-id and brand-id that results in a price, then a 200 response is received with the price in the body")
    public void validRequestReturnOkWithPrice() throws Exception {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        when(getPricePort.get(date, productId, brandId)).thenReturn(Optional.of(PriceMother.PRICE_1));
        when(priceMapper.toPricesResponse(PriceMother.PRICE_1)).thenReturn(PricesResponseMother.PRICES_RESPONSE_1);

        mockMvc.perform(get("/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product-id", productId)
                        .queryParam("brand-id", brandId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.product-id", is("35455")))
                .andExpect(jsonPath("$.brand-id", is("1")))
                .andExpect(jsonPath("$.price-list", is("1")))
                .andExpect(jsonPath("$.start-date", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.end-date", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is("EUR")));
    }

    @Test
    @DisplayName("Given a valid date, product-id and brand-id that don't find any price, then a 204 response is received")
    public void validRequestReturnNoContent() throws Exception {

        LocalDateTime date = LocalDateTime.of(2020, 6, 14, 10, 0, 0);
        String productId = "35455";
        String brandId = "1";

        mockMvc.perform(get("/prices")
                        .queryParam("date", date.toString())
                        .queryParam("product-id", productId)
                        .queryParam("brand-id", brandId))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Given something that is not a date, then a 400 response is received")
    public void somethingThatIsNotADateReturnsKo() throws Exception {
        mockMvc.perform(get("/prices")
                        .queryParam("date", "An invalid date")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given a date with an invalid format, then a 400 response is received")
    public void invalidDateReturnsKo() throws Exception {
        mockMvc.perform(get("/prices")
                        .queryParam("date", "2020/06/14T10:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given a request without date, then a 400 response is received")
    public void withoutDateReturnsKo() throws Exception {
        mockMvc.perform(get("/prices")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given a request without product-id, then a 400 response is received")
    public void withoutPriceIdReturnsKo() throws Exception {
        mockMvc.perform(get("/prices")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .queryParam("brand-id", "1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Given a request without brand-id, then a 400 response is received")
    public void withoutBrandIdReturnsKo() throws Exception {
        mockMvc.perform(get("/prices")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .queryParam("product-id", "35455"))
                .andExpect(status().isBadRequest());
    }

}
