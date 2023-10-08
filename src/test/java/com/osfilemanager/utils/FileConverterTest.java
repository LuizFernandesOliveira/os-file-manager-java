package com.osfilemanager.utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.multipart.MultipartFile;

@DisplayName("UTILS - FileConverter")
class FileConverterTest {

  @Test
  void convert() throws IOException {
    MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

    given(multipartFile.getBytes()).willReturn(new byte[0]);

    File fileDTO = FileConverter.of(multipartFile, "file.txt");

    assertNotNull(fileDTO);
    assert fileDTO.delete();
  }

  @Test
  void notConvert() throws IOException {
    MultipartFile multipartFile = Mockito.mock(MultipartFile.class);

    doThrow(IOException.class).when(multipartFile).getBytes();

    assertThrows(
        InternalServerErrorException.class, () -> FileConverter.of(multipartFile, "file.txt"));
  }
}
