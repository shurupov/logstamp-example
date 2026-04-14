package com.logging.presentation.camunda.orchestrator.controller;

import com.logging.presentation.api.OrchestratorApi;
import com.logging.presentation.api.request.OrchestratorStartClaimRequest;
import com.logging.presentation.api.response.OrchestratorStartClaimResponse;
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
  public OrchestratorStartClaimResponse createClaim(OrchestratorStartClaimRequest request) {
    if (request.getClaimId() == null) {
      request.setClaimId(UUID.randomUUID());
      stampContext.add("claimId", request.getClaimId().toString());
    }

    runtimeService.startProcessInstanceByKey(
        "process",
        request.getClaimId().toString(),
        Map.of("claimId", request.getClaimId(), "clientId", request.getClientId())
    );
    return new OrchestratorStartClaimResponse(request.getClaimId());
  }

  @Override
  public void delivered(UUID claimId) {
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
        .processInstanceBusinessKey(claimId.toString())
        .singleResult();
    if (processInstance != null) {
      runtimeService.createMessageCorrelation("DELIVERED")
          .processInstanceId(processInstance.getProcessInstanceId())
          .correlate();
    }
  }
}
