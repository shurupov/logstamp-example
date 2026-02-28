package com.logging.presentation.logging.starter.cross.identifier.interceptor.pass;

import com.logging.presentation.logging.starter.cross.identifier.ContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.logging.presentation.logging.starter.cross.identifier.IdentifierConverter.toKebabCase;

@RequiredArgsConstructor
@Component
public class ContextFeignRequestInterceptor implements RequestInterceptor {

  @Value("${logging-context.header-name-prefix:x-id-}")
  private final String headerNamePrefix;
  private final ContextHolder contextHolder;

  @Override
  public void apply(RequestTemplate template) {
    for (Map.Entry<String, String> contextEntry : contextHolder.get().entrySet()) {
      template.header(
          headerNamePrefix + toKebabCase(contextEntry.getKey()),
          contextEntry.getValue()
      );
    }
  }
}
