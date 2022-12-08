package me.dserrano.inditex.prices.infrastructure.outbound.h2.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@Configuration
public class PricesDaoAdapterConfig {
    @Bean
    public ResourceDatabasePopulator databasePopulator() {
        return new ResourceDatabasePopulator(new ClassPathResource("data.sql"));
    }

    @Bean
    public ConnectionFactoryInitializer databaseInitializer(
            ConnectionFactory connectionFactory,
            ResourceDatabasePopulator populator
    ) {
        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }
}
