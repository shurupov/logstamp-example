package com.logging.presentation.client.adapter.controller;

import com.logging.presentation.api.ClientAdapterApi;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import com.logging.presentation.client.adapter.feign.ClientExternalSystemClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ClientController implements ClientAdapterApi {

    private final ClientExternalSystemClient clientExternalSystemClient;

    @Override
    public ClientAdapterClientResponse getClient(Long clientId) {
        return clientExternalSystemClient.getClient(clientId);
    }
}
