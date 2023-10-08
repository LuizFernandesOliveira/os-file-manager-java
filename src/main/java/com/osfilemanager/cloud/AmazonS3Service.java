package com.osfilemanager.cloud;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.io.File;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AmazonS3Service {

  private final AmazonS3 amazonS3;

  @Value("${spring.cloud.aws.s3.bucket.name}")
  private String bucketName;

  public void create(final String filename, final File file) {
    var awsObj = new PutObjectRequest(bucketName, filename, file);
    amazonS3.putObject(awsObj);
  }

  public URI retrieve(String fileName) {
    var obj = amazonS3.getObject(bucketName, fileName);
    return obj.getObjectContent().getHttpRequest().getURI();
  }
}
