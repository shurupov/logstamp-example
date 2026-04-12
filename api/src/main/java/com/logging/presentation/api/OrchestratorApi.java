package com.logging.presentation.api;

import com.logging.presentation.api.request.OrchestratorStartClaimRequest;
import com.logging.presentation.api.response.OrchestratorStartClaimResponse;
import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface OrchestratorApi {

  @PostMapping("/v1/claims")
  OrchestratorStartClaimResponse createClaim(OrchestratorStartClaimRequest request);

  @PostMapping("/v1/claims/{claimId}/delivered")
  void delivered(@PathVariable(name = "claimId") UUID claimId);
}
