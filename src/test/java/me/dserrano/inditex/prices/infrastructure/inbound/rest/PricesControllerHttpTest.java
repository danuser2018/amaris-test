package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PricesController.class)
public class PricesControllerHttpTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Given a valid date, product-id and brand-id that results in a price, then a 200 response is received with the price in the body")
    public void validRequestReturnOkWithPrice() throws Exception {
        // Given
        // When
        ResultActions result = mockMvc.perform(get("/prices")
                .queryParam("date", "2020-06-14T10:00:00")
                .queryParam("product-id", "35455")
                .queryParam("brand-id", "1"));

        // Then
        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.product-id", is("35455")))
                .andExpect(jsonPath("$.brand-id", is("1")))
                .andExpect(jsonPath("$.price-list", is("1")))
                .andExpect(jsonPath("$.start-date", is("2020-06-14T00:00:00")))
                .andExpect(jsonPath("$.end-date", is("2020-12-31T23:59:59")))
                .andExpect(jsonPath("$.price", is(35.5)))
                .andExpect(jsonPath("$.currency", is("EUR")));
    }


}
