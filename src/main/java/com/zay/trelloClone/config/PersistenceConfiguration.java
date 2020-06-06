package com.zay.trelloClone.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PersistenceConfiguration {
    @Bean
    public DataSource dataSource() {
        DataSourceBuilder builder = DataSourceBuilder.create();
        builder.url("jdbc:mysql://localhost:3306/trelloclone");
        builder.username("root");
        builder.password("Zay@#4410");
        System.out.println("Your database has been initialized");
        return builder.build();
    }
}

