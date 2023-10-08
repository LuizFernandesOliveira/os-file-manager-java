package com.osfilemanager.api.http;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
@Tag(name = "File")
public interface FileHTTP {
  String RESOURCE = "/api/v1/files";
}
