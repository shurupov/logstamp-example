package com.logging.presentation.api.request;

import lombok.Data;

import java.util.UUID;

@Data
public class MainServiceStartClaimRequest {
    private Long clientId;
    private UUID requestId;
}
