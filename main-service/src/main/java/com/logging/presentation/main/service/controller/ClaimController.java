package com.logging.presentation.main.service.controller;

import com.logging.presentation.api.MainServiceApi;
import com.logging.presentation.api.request.DeliveryCompletedCallbackRequest;
import com.logging.presentation.api.request.MainServiceStartClaimRequest;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import com.logging.presentation.api.response.MainServiceOneStartClaimResponse;
import com.logging.presentation.logging.starter.cross.identifier.ContextHolder;
import com.logging.presentation.main.service.feign.ClientAdapterClient;
import com.logging.presentation.main.service.scheduled.ScheduledService;
import com.logging.presentation.main.service.service.DeliveryService;
import com.logging.presentation.main.service.service.KafkaProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClaimController implements MainServiceApi {

    private final ClientAdapterClient clientAdapterClient;
    private final DeliveryService deliveryService;
    private final ScheduledService scheduledService;
    private final ContextHolder contextHolder;
    private final KafkaProducer kafkaProducer;

    @Override
    public MainServiceOneStartClaimResponse start(MainServiceStartClaimRequest startClaimRequest) {
        UUID claimId = UUID.randomUUID();
        contextHolder.add("claimId", claimId.toString());
        ClientAdapterClientResponse client = clientAdapterClient.getClient(startClaimRequest.getClientId());
        deliveryService.startDelivery(claimId, client);
        scheduledService.addJob(claimId, "Тестовый текст");
        kafkaProducer.sendMessage(new KafkaProducer.ClaimEvent(claimId, "Текст сообщения"));
        return new MainServiceOneStartClaimResponse(claimId);
    }

    @Override
    public void delivered(UUID claimId, DeliveryCompletedCallbackRequest deliveryCallbackRequest) {
        log.info("Успех!");
    }
}
