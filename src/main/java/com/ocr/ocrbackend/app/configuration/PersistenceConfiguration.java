package com.ocr.ocrbackend.app.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.ocr.ocrbackend.app.repository"})
@EnableJpaAuditing
@Primary
public class PersistenceConfiguration {
}
