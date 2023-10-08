package com.osfilemanager.interactions;

import com.osfilemanager.cloud.AmazonS3Service;
import com.osfilemanager.interactions.input.CreateInput;
import com.osfilemanager.interactions.output.CreateOutput;
import com.osfilemanager.utils.FileConverter;
import java.io.File;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FileCreate {
  private final AmazonS3Service amazonS3Service;

  public CreateOutput execute(final CreateInput input) {
    String filename = UUID.randomUUID().toString();

    saveFile(input, filename);

    return retrieveFile(filename);
  }

  private void saveFile(CreateInput input, String filename) {
    File fileConverted = FileConverter.of(input.getFile(), filename);
    amazonS3Service.create(filename, fileConverted);
    assert fileConverted.delete();
  }

  private CreateOutput retrieveFile(String filename) {
    final var url = amazonS3Service.retrieve(filename);
    return CreateOutput.builder().url(url.toString()).build();
  }
}
