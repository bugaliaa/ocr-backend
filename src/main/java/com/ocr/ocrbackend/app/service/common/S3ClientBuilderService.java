package com.ocr.ocrbackend.app.service.common;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class S3ClientBuilderService {

    @Value("${cloud.aws.credentials.access-key}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secret-key}")
    private String secretKey;

    @Value("${cloud.aws.s3-bucket}")
    private String bucketName;

    @Value("${cloud.aws.s3-common-bucket}")
    private String commonBucket;

    //TODO: Auto update the morning digest urls in the table
    public AmazonS3 buildS3Client() {
        return com.amazonaws.services.s3.AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(accessKey, secretKey)))
                .withRegion(Regions.AP_SOUTH_1)
                .build();
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getCommonBucket() {
        return commonBucket;
    }
}
