package com.logging.presentation.service_one.controller;

import com.logging.presentation.api.ServiceOneApi;
import com.logging.presentation.api.request.ServiceOneStartClaimRequest;
import com.logging.presentation.api.response.ServiceOneStartClaimResponse;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class ClaimController implements ServiceOneApi {

    @Override
    public ServiceOneStartClaimResponse start(ServiceOneStartClaimRequest startClaimRequest) {
        UUID claimId = UUID.randomUUID();
        MDC.put("claimId", claimId.toString());
        log.info("Получено {}", startClaimRequest);
        log.error("Исключение", new RuntimeException("Что-то пошло не так"));
        MDC.remove("claimId");
        return new ServiceOneStartClaimResponse(claimId);
    }
}
