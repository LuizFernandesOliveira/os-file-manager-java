package com.osfilemanager.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AmazonS3Config {
  @Value("${spring.cloud.aws.credentials.access-key}")
  private String accessKey;

  @Value("${spring.cloud.aws.credentials.secret-key}")
  private String secretKey;

  @Bean
  public AmazonS3 amazonS3() {
    BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKey, secretKey);
    AWSStaticCredentialsProvider awsStaticCredentialsProvider =
        new AWSStaticCredentialsProvider(basicAWSCredentials);
    return AmazonS3ClientBuilder.standard()
        .withCredentials(awsStaticCredentialsProvider)
        .withRegion(Regions.US_EAST_1)
        .build();
  }
}
