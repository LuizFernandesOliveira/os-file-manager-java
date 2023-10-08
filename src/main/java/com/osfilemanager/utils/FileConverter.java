package com.osfilemanager.utils;

import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FileConverter {
  public static File of(final MultipartFile file, final String filename) {
    File fileConverted = new File(Objects.requireNonNull(filename));

    try (FileOutputStream fos = new FileOutputStream(fileConverted)) {
      fos.write(file.getBytes());
    } catch (IOException e) {
      log.info(e.getMessage());
      assert fileConverted.delete();
      throw new InternalServerErrorException("File not found");
    }

    return fileConverted;
  }
}
