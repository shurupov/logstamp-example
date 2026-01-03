package com.logging.presentation.api;

import com.logging.presentation.api.request.ServiceOneStartClaimRequest;
import com.logging.presentation.api.response.ServiceOneStartClaimResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface ServiceOneApi {

    @PostMapping("/claims")
    ServiceOneStartClaimResponse start(@RequestBody ServiceOneStartClaimRequest startClaimRequest);
}
