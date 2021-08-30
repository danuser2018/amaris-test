package me.dserrano.inditex.prices;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PricesApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Test
    @DisplayName("Petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)")
    void test1() throws Exception {
        mvc.perform(
                get("/prices")
                        .queryParam("date", "2020-06-14T10:00:00")
                        .queryParam("product-id", "35455")
                        .queryParam("brand-id", "1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
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
