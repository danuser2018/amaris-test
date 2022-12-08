package me.dserrano.inditex.prices.domain.config;

import me.dserrano.inditex.prices.domain.model.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricesServiceConfigTest {

    private Comparator<Price> priorityComparator;

    @BeforeEach
    void setup() {
        priorityComparator = new PricesServiceConfig().priorityComparator();
    }

    @Test
    @DisplayName("Given 2 prices with same priority then return that are equals")
    void test2PricesWithSamePriority() {
        // Given
        Price price1 = Price.builder().priority(0).build();
        Price price2 = Price.builder().priority(0).build();

        // When
        int result = priorityComparator.compare(price1, price2);

        // Then
        assertEquals(0, result);
    }

    @Test
    @DisplayName("Given 2 prices with the first with more priority than the second then return that the first is greater")
    void test2PricesFirstWithMorePriority() {
        // Given
        Price price1 = Price.builder().priority(1).build();
        Price price2 = Price.builder().priority(0).build();

        // When
        int result = priorityComparator.compare(price1, price2);

        // Then
        assertEquals(1, result);
    }

    @Test
    @DisplayName("Given 2 prices with the second with more priority than the first then return that the first is smaller")
    void test2PricesSecondWithMorePriority() {
        // Given
        Price price1 = Price.builder().priority(0).build();
        Price price2 = Price.builder().priority(1).build();

        // When
        int result = priorityComparator.compare(price1, price2);

        // Then
        assertEquals(-1, result);
    }
}