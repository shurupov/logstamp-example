package com.logging.presentation.api;

import com.logging.presentation.api.request.DeliveryCompletedCallbackRequest;
import com.logging.presentation.api.request.MainServiceStartClaimRequest;
import com.logging.presentation.api.response.MainServiceOneStartClaimResponse;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

public interface MainServiceApi {

    @PostMapping("/v1/claims")
    MainServiceOneStartClaimResponse start(@RequestBody MainServiceStartClaimRequest startClaimRequest);

    @PatchMapping("/v1/claims/{claimId}/delivered")
    void delivered(@PathVariable(name = "claimId") UUID claimId, @RequestBody DeliveryCompletedCallbackRequest deliveryCallbackRequest);
}
