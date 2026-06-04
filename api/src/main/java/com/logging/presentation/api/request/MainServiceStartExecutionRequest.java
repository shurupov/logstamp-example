package com.logging.presentation.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MainServiceStartExecutionRequest {
  private UUID executionId;
  private Long clientId;
}
