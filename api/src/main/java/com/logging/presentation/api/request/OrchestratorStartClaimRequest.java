package com.logging.presentation.api.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrchestratorStartClaimRequest {
  private UUID claimId;
  private Long clientId;
}
