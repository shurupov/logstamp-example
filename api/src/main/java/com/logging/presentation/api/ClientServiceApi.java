package com.logging.presentation.api;

import com.logging.presentation.api.response.ClientServiceClientResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ClientServiceApi {

    @GetMapping("/v1/clients/{clientId}")
    ClientServiceClientResponse getClient(@PathVariable(name = "clientId") Long clientId);
}
