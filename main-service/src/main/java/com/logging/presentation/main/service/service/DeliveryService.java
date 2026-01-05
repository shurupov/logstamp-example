package com.logging.presentation.main.service.service;

import com.logging.presentation.api.request.StartDeliveryRequest;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import com.logging.presentation.main.service.feign.DeliveryAdapterClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryAdapterClient deliveryAdapterClient;

    @Async
    public void startDelivery(UUID claimId, ClientAdapterClientResponse client) {
        StartDeliveryRequest request = new StartDeliveryRequest(
                claimId,
                client.getFirstName() + " " + client.getLastName(),
                client.getPhone(),
                client.getAddress()
        );
        deliveryAdapterClient.startDelivery(request);
    }

}
