package me.dserrano.inditex.prices.domain.config;

import me.dserrano.inditex.prices.domain.model.Price;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;

@Configuration
public class PricesServiceConfig {
    @Bean
    Comparator<Price> priorityComparator() {
        return Comparator.comparingInt(Price::getPriority);
    }
}
