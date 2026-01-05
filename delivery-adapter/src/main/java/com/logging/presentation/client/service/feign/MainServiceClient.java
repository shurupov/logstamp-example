package com.logging.presentation.client.service.feign;

import com.logging.presentation.api.MainServiceApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "mainServiceClient", url = "${feign.main-service}")
public interface MainServiceClient extends MainServiceApi {
}
