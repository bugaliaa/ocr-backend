package com.ocr.ocrbackend.app.configuration;

import com.ocr.ocrbackend.app.properties.DataSourceProperties;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class DataSourceConfiguration {

    @NonNull
    private final DataSourceProperties dataSourceProperties;

    @Bean
    @Primary
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
        dataSource.setUsername(dataSourceProperties.getUsername());
        dataSource.setPassword(dataSourceProperties.getPassword());
        dataSource.setJdbcUrl(dataSourceProperties.getUrl());

        return dataSource;
    }
}
