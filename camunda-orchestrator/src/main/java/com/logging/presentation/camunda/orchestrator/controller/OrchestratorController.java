package com.logging.presentation.camunda.orchestrator.controller;

import com.logging.presentation.api.OrchestratorApi;
import com.logging.presentation.api.request.OrchestratorStartClaimRequest;
import com.logging.presentation.api.response.OrchestratorStartClaimResponse;
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

  @Override
  public OrchestratorStartClaimResponse createClaim(OrchestratorStartClaimRequest request) {
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
        "process",
        Map.of("clientId", request.getClientId().toString())
    );
    return new OrchestratorStartClaimResponse(UUID.fromString(processInstance.getProcessInstanceId()));
  }

  @Override
  public void delivered(UUID claimId) {
    runtimeService.createMessageCorrelation("DELIVERED")
        .processInstanceId(claimId.toString())
        .correlate();
  }
}
