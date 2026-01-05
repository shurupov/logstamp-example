package com.logging.presentation.client.service.controller;

import com.logging.presentation.api.DeliveryAdapterApi;
import com.logging.presentation.api.request.DeliveryCompletedCallbackRequest;
import com.logging.presentation.api.request.StartDeliveryRequest;
import com.logging.presentation.client.service.feign.DeliveryExternalSystemClient;
import com.logging.presentation.client.service.feign.MainServiceClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DeliveryController implements DeliveryAdapterApi {

    private final DeliveryExternalSystemClient deliveryExternalSystemClient;
    private final MainServiceClient mainServiceClient;

    @Override
    public void startDelivery(StartDeliveryRequest startDeliveryRequest) {
        deliveryExternalSystemClient.startDelivery(startDeliveryRequest);
    }

    @Override
    public void delivered(UUID claimId, DeliveryCompletedCallbackRequest deliveryCallbackRequest) {
        mainServiceClient.delivered(claimId, deliveryCallbackRequest);
    }
}
