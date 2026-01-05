package com.logging.presentation.main.service.controller;

import com.logging.presentation.api.ServiceOneApi;
import com.logging.presentation.api.request.ServiceOneStartClaimRequest;
import com.logging.presentation.api.response.ServiceOneStartClaimResponse;
import com.logging.presentation.logging.starter.cross.identifier.ContextHolder;
import com.logging.presentation.main.service.feign.ClientAdapterClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClaimController implements ServiceOneApi {

    private final ClientAdapterClient serviceTwoClient;
    private final ContextHolder contextHolder;

    @Override
    public ServiceOneStartClaimResponse start(ServiceOneStartClaimRequest startClaimRequest) {
        UUID claimId = UUID.randomUUID();
        contextHolder.add("claimId", claimId.toString());
        serviceTwoClient.getClient(startClaimRequest.getClientId());
        return new ServiceOneStartClaimResponse(claimId);
    }
}
