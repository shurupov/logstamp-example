package com.logging.presentation.api;

import com.logging.presentation.api.request.StartDeliveryRequest;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

public interface ExternalSystemApi {

    @GetMapping("/v1/clients/{clientId}")
    ClientAdapterClientResponse getClient(@PathVariable(name = "clientId") Long clientId);

    @PostMapping("/v1/deliveries")
    void startDelivery(StartDeliveryRequest startDeliveryRequest);
}
