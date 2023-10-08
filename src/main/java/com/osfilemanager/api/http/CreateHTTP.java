package com.osfilemanager.api.http;

import com.osfilemanager.api.http.output.CreateHTTPOutput;
import com.osfilemanager.interactions.FileCreate;
import com.osfilemanager.interactions.input.CreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class CreateHTTP implements FileHTTP {
  private final FileCreate interaction;

  @PostMapping(
      path = RESOURCE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public CreateHTTPOutput execute(@RequestParam("file") MultipartFile file) {
    final var input = CreateInput.builder().file(file).build();

    return interaction.execute(input).toHTTPOutput();
  }
}
