package com.logging.presentation.client.service.feign;

import com.logging.presentation.api.ClientAdapterApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "clientExternalSystem", url = "${feign.client-external-system}")
public interface ClientExternalSystemClient extends ClientAdapterApi {
}
