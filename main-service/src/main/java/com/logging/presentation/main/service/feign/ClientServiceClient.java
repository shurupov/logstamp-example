package com.logging.presentation.main.service.feign;

import com.logging.presentation.api.ClientServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "serviceTwo", url = "${feign.service-two}")
public interface ClientServiceClient extends ClientServiceApi {
}
