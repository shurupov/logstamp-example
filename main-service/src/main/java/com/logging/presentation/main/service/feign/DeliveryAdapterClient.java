package com.logging.presentation.main.service.feign;

import com.logging.presentation.api.DeliveryAdapterApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "deliveryAdapter", url = "${feign.delivery-adapter}")
public interface DeliveryAdapterClient extends DeliveryAdapterApi {
}
