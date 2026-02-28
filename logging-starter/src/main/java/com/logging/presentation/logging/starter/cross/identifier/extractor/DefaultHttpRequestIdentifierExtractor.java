package com.logging.presentation.logging.starter.cross.identifier.extractor;

import static com.logging.presentation.logging.starter.cross.identifier.IdentifierConverter.toCamelCase;

import com.logging.presentation.logging.starter.cross.identifier.CachedBodyHttpServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultHttpRequestIdentifierExtractor implements HttpRequestIdentifierExtractor {

  @Value("${logging-context.header-name-prefix:x-id-}")
  private final String headerNamePrefix;

  @Override
  public Map<String, String> typedExtract(CachedBodyHttpServletRequest request) {
    Map<String, String> identifiers = new HashMap<>();
    for (String headerName: Collections.list(request.getHeaderNames())) {
      if (!headerName.startsWith(headerNamePrefix)) {
        continue;
      }
      extractFromHeader(identifiers, headerName, request);
    }
    return identifiers;
  }

  private void extractFromHeader(Map<String, String> identifiers, String headerName, HttpServletRequest request) {
    String headerValue = request.getHeader(headerName);
    String contextEntryName = toCamelCase(headerName.substring(headerNamePrefix.length()));
    identifiers.put(contextEntryName, headerValue);
  }
}
