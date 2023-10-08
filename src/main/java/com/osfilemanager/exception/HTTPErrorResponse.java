package com.osfilemanager.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class HTTPErrorResponse {
  private String message;
}
