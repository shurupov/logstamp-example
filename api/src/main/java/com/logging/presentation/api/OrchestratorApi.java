package com.logging.presentation.api;

import com.logging.presentation.api.request.OrchestratorStartExecutionRequest;
import com.logging.presentation.api.response.OrchestratorStartExecutionResponse;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrchestratorApi {

  @PostMapping("/v1/executions")
  OrchestratorStartExecutionResponse createClaim(@RequestBody OrchestratorStartExecutionRequest request);

  @PostMapping("/v1/executions/{executionId}/delivered")
  void delivered(@PathVariable(name = "executionId") UUID executionId);
}
