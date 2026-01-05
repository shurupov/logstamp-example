package com.logging.presentation.client.service.controller;

import com.logging.presentation.api.ClientAdapterApi;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class ClientController implements ClientAdapterApi {
    @Override
    public ClientAdapterClientResponse getClient(Long clientId) {

        Faker faker = new Faker(new Locale("ru_RU"));

        return ClientAdapterClientResponse.builder()
                .clientId(clientId)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .build();
    }
}
