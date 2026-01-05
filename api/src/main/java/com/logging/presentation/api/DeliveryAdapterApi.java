package com.logging.presentation.api;

import com.logging.presentation.api.request.DeliveryCompletedCallbackRequest;
import com.logging.presentation.api.request.StartDeliveryRequest;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface DeliveryAdapterApi {

    @PostMapping("/v1/deliveries")
    void startDelivery(StartDeliveryRequest startDeliveryRequest);

    @PatchMapping("/v1/deliveries/{claimId}/delivered")
    void delivered(@PathVariable(name = "claimId") UUID claimId, @RequestBody DeliveryCompletedCallbackRequest deliveryCallbackRequest);
}
