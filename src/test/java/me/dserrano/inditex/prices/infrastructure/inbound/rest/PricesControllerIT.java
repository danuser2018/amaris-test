package me.dserrano.inditex.prices.infrastructure.inbound.rest;

import me.dserrano.inditex.prices.domain.ports.primary.GetPricePort;
import me.dserrano.inditex.prices.infrastructure.inbound.rest.mapper.PriceMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        mockMvc.perform(get("/prices")
                .queryParam("date", "2020-06-14T10:00:00")
                .queryParam("product-id", "35455")
                .queryParam("brand-id", "1"))
                .andExpect(status().isOk());
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
