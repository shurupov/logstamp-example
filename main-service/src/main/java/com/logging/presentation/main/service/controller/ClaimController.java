package com.logging.presentation.main.service.controller;

import com.logging.presentation.api.MainServiceApi;
import com.logging.presentation.api.kafka.ExecutionEvent;
import com.logging.presentation.api.request.DeliveryCompletedCallbackRequest;
import com.logging.presentation.api.request.MainServiceStartExecutionRequest;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import com.logging.presentation.api.response.MainServiceOneStartExecutionResponse;
import com.logging.presentation.main.service.feign.ClientAdapterClient;
import com.logging.presentation.main.service.feign.OrchestratorClient;
import com.logging.presentation.main.service.scheduled.ScheduledService;
import com.logging.presentation.main.service.service.DeliveryService;
import com.logging.presentation.main.service.service.KafkaProducer;
import io.github.shurupov.logstamp.core.StampContext;
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
    private final StampContext stampContext;
    private final KafkaProducer kafkaProducer;
    private final OrchestratorClient orchestratorClient;

    @Override
    public MainServiceOneStartExecutionResponse start(MainServiceStartExecutionRequest startExecutionRequest) {
        UUID executionId = startExecutionRequest.getExecutionId();
        if (executionId == null) {
          executionId = UUID.randomUUID();
          stampContext.add("executionId", executionId.toString());
          log.info("Caught request {}", startExecutionRequest);
        }
        ClientAdapterClientResponse client = clientAdapterClient.getClient(startExecutionRequest.getClientId());
        deliveryService.startDelivery(executionId, client);
        scheduledService.addJob(executionId, "Тестовый текст");
        kafkaProducer.sendMessage(new ExecutionEvent(executionId, "Текст сообщения"));
        return new MainServiceOneStartExecutionResponse(executionId);
    }

    @Override
    public void delivered(UUID executionId, DeliveryCompletedCallbackRequest deliveryCallbackRequest) {
      orchestratorClient.delivered(executionId);
      log.info("Успех!");
    }
}
