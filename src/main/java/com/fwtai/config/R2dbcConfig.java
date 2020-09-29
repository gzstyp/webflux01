package com.fwtai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.NamingStrategy;
import org.springframework.data.relational.core.mapping.RelationalPersistentProperty;

@Configuration
public class R2dbcConfig {

    @Bean
    public NamingStrategy namingStrategy() {
        return new NamingStrategy() {
            @Override
            public String getColumnName(final RelationalPersistentProperty property) {
                return property.getName();
            }
        };
    }
}