package com.osfilemanager.interactions.output;

import com.osfilemanager.api.http.output.CreateFileHTTPOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOutput {
  public String url;

  public CreateFileHTTPOutput toHTTPOutput() {
    return CreateFileHTTPOutput.builder().url(url).build();
  }
}
