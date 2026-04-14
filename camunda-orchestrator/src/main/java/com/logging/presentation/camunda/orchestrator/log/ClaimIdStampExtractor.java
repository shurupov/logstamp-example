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
public class ClaimIdStampExtractor implements HttpRequestStampExtractor {

  private final ObjectMapper objectMapper;

  @Override
  public Map<String, String> typedExtract(CachedBodyHttpServletRequest container) throws Exception {
    String body = container.getBodyAsString();
    try {
      ClaimIdContainer claimIdContainer = objectMapper.readValue(body, ClaimIdContainer.class);
      if (claimIdContainer.claimId != null) {
        return Map.of("claimId", claimIdContainer.claimId.toString());
      }
    } catch (Exception ignore) {
    }
    return Map.of();
  }

  @Data
  protected static class ClaimIdContainer {
    private UUID claimId;
  }
}
