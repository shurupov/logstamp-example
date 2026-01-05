package com.logging.presentation.main.service.feign;

import com.logging.presentation.api.ClientAdapterApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "clientAdapter", url = "${feign.client-adapter}")
public interface ClientAdapterClient extends ClientAdapterApi {
}
