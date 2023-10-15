package com.osfilemanager.api.http;

import com.osfilemanager.api.http.output.CreateFileHTTPOutput;
import com.osfilemanager.interactions.FileCreate;
import com.osfilemanager.interactions.input.CreateInput;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@CrossOrigin(
    origins = "*",
    methods = {RequestMethod.POST})
public class CreateFileHTTP implements FileHTTP {
  private final FileCreate interaction;

  @PostMapping(
      path = RESOURCE,
      consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public CreateFileHTTPOutput execute(@RequestParam("file") MultipartFile file) {
    final var input = CreateInput.builder().file(file).build();

    return interaction.execute(input).toHTTPOutput();
  }
}
