package com.logging.presentation.external.system.controller;

import com.logging.presentation.api.ExternalSystemApi;
import com.logging.presentation.api.request.StartDeliveryRequest;
import com.logging.presentation.api.response.ClientAdapterClientResponse;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class ExternalSystemController implements ExternalSystemApi {
    @Override
    public ClientAdapterClientResponse getClient(Long clientId) {

        Faker faker = new Faker(new Locale("ru_RU"));

        return ClientAdapterClientResponse.builder()
                .clientId(clientId)
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .phone(faker.phoneNumber().cellPhone())
                .address(faker.address().fullAddress())
                .build();
    }

    @Override
    public void startDelivery(StartDeliveryRequest startDeliveryRequest) {
        log.info("Принял доставку {}", startDeliveryRequest);
    }
}
