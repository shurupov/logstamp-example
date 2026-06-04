package com.logging.presentation.api.request;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrchestratorStartExecutionRequest {
  private UUID executionId;
  private Long clientId;
}
