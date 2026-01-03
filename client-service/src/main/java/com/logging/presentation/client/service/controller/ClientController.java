package com.logging.presentation.client.service.controller;

import com.logging.presentation.api.ClientServiceApi;
import com.logging.presentation.api.response.ClientServiceClientResponse;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class ClientController implements ClientServiceApi {
    @Override
    public ClientServiceClientResponse getClient(Long clientId) {

        Faker faker = new Faker(new Locale("ru_RU"));

        return ClientServiceClientResponse.builder()
                .clientId(clientId)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
