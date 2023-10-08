package com.osfilemanager.interactions.output;

import com.osfilemanager.api.http.output.CreateHTTPOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOutput {
  public String url;

  public CreateHTTPOutput toHTTPOutput() {
    return CreateHTTPOutput.builder().url(url).build();
  }
}
