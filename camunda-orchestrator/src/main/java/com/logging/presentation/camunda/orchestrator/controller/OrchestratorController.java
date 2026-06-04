package com.logging.presentation.camunda.orchestrator.controller;

import com.logging.presentation.api.OrchestratorApi;
import com.logging.presentation.api.request.OrchestratorStartExecutionRequest;
import com.logging.presentation.api.response.OrchestratorStartExecutionResponse;
import io.github.shurupov.logstamp.core.StampContext;
import java.util.Map;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrchestratorController implements OrchestratorApi {

  private final RuntimeService runtimeService;
  private final StampContext stampContext;

  @Override
  public OrchestratorStartExecutionResponse createClaim(OrchestratorStartExecutionRequest request) {
    if (request.getExecutionId() == null) {
      request.setExecutionId(UUID.randomUUID());
      stampContext.add("executionId", request.getExecutionId().toString());
    }

    runtimeService.startProcessInstanceByKey(
        "process",
        request.getExecutionId().toString(),
        Map.of("executionId", request.getExecutionId(), "clientId", request.getClientId())
    );
    return new OrchestratorStartExecutionResponse(request.getExecutionId());
  }

  @Override
  public void delivered(UUID executionId) {
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
        .processInstanceBusinessKey(executionId.toString())
        .singleResult();
    if (processInstance != null) {
      runtimeService.createMessageCorrelation("DELIVERED")
          .processInstanceId(processInstance.getProcessInstanceId())
          .correlate();
    }
  }
}
