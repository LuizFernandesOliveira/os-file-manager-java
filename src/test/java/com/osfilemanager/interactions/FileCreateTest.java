package com.osfilemanager.interactions;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.doNothing;

import com.osfilemanager.cloud.AmazonS3Service;
import com.osfilemanager.interactions.input.CreateInput;
import com.osfilemanager.interactions.output.CreateOutput;
import java.io.IOException;
import java.net.URI;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

@ExtendWith(MockitoExtension.class)
@DisplayName("INTERACTIONS - FileCreate")
class FileCreateTest {
  @Mock private AmazonS3Service amazonS3Service;

  @InjectMocks private FileCreate fileCreate;

  @Test
  void execute() throws IOException {
    URI uri = URI.create("");
    MultipartFile multipartFile = Mockito.mock(MultipartFile.class);
    CreateInput input = CreateInput.builder().file(multipartFile).build();

    doNothing().when(amazonS3Service).create(any(), any());
    given(amazonS3Service.retrieve(any())).willReturn(uri);
    given(multipartFile.getBytes()).willReturn(new byte[0]);

    CreateOutput output = fileCreate.execute(input);

    then(amazonS3Service).should().create(any(), any());
    then(amazonS3Service).should().retrieve(any());
    assertNotNull(output);
    assertEquals(uri.toString(), output.getUrl());
  }
}
