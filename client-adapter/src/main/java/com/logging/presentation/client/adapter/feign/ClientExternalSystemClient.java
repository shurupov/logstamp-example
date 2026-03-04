package com.logging.presentation.client.adapter.feign;

import com.logging.presentation.api.ExternalSystemApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "clientExternalSystem", url = "${feign.external-system}")
public interface ClientExternalSystemClient extends ExternalSystemApi {
}
