package com.ocr.ocrbackend.app.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AWSConfiguration {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Bean
    @Primary
    public AmazonSNSClient amazonSNSClient() {
        AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(accessKey, secretKey));

        return (AmazonSNSClient) AmazonSNSClient.builder()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }
}
