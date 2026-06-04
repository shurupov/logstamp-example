package com.logging.presentation.camunda.orchestrator.log;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.shurupov.logstamp.CachedBodyHttpServletRequest;
import io.github.shurupov.logstamp.extractor.HttpRequestStampExtractor;
import java.util.Map;
import java.util.UUID;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExecutionIdStampExtractor implements HttpRequestStampExtractor {

  private final ObjectMapper objectMapper;

  @Override
  public Map<String, String> typedExtract(CachedBodyHttpServletRequest container) throws Exception {
    String body = container.getBodyAsString();
    try {
      ExecutionIdContainer executionIdContainer = objectMapper.readValue(body, ExecutionIdContainer.class);
      if (executionIdContainer.executionId != null) {
        return Map.of("executionId", executionIdContainer.executionId.toString());
      }
    } catch (Exception ignore) {
    }
    return Map.of();
  }

  @Data
  protected static class ExecutionIdContainer {
    private UUID executionId;
  }
}
