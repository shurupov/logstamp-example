package com.logging.presentation.main.service.controller;

import com.logging.presentation.api.ServiceOneApi;
import com.logging.presentation.api.request.ServiceOneStartClaimRequest;
import com.logging.presentation.api.response.ServiceOneStartClaimResponse;
import com.logging.presentation.main.service.feign.ClientServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClaimController implements ServiceOneApi {

    private final ClientServiceClient serviceTwoClient;

    @Override
    public ServiceOneStartClaimResponse start(ServiceOneStartClaimRequest startClaimRequest) {
        UUID claimId = UUID.randomUUID();
        serviceTwoClient.getClient(startClaimRequest.getClientId());
        return new ServiceOneStartClaimResponse(claimId);
    }
}
