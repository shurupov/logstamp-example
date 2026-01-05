package com.logging.presentation.client.service.feign;

import com.logging.presentation.api.ExternalSystemApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "externalSystemClient", url = "${feign.external-system}")
public interface DeliveryExternalSystemClient extends ExternalSystemApi {
}
