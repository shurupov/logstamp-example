package com.logging.presentation.service_one.controller;

import com.logging.presentation.service_one.controller.request.StartClaimRequest;
import com.logging.presentation.service_one.controller.response.ClaimIdResponse;
import lombok.extern.slf4j.Slf4j;
import org.jboss.logging.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class ClaimController {

    @PostMapping("/claims")
    public ClaimIdResponse start(@RequestBody StartClaimRequest startClaimRequest) {
        UUID claimId = UUID.randomUUID();
        MDC.put("claimId", claimId.toString());
        log.info("Получено {}", startClaimRequest);
        log.error("Исключение", new RuntimeException("Что-то пошло не так"));
        MDC.remove("claimId");
        return new ClaimIdResponse(claimId);
    }
}
