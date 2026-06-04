package com.logging.presentation.api;

import com.logging.presentation.api.request.DeliveryCompletedCallbackRequest;
import com.logging.presentation.api.request.MainServiceStartExecutionRequest;
import com.logging.presentation.api.response.MainServiceOneStartExecutionResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface MainServiceApi {

    @PostMapping("/v1/executions")
    MainServiceOneStartExecutionResponse start(@RequestBody MainServiceStartExecutionRequest startExecutionRequest);

    @PatchMapping("/v1/executions/{executionId}/delivered")
    void delivered(@PathVariable(name = "executionId") UUID executionId, @RequestBody DeliveryCompletedCallbackRequest deliveryCallbackRequest);
}
