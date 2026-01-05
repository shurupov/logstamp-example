package com.logging.presentation.api;

import com.logging.presentation.api.response.ClientAdapterClientResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface ClientAdapterApi {

    @GetMapping("/v1/clients/{clientId}")
    ClientAdapterClientResponse getClient(@PathVariable(name = "clientId") Long clientId);
}
