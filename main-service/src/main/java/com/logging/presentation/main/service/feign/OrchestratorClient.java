package com.logging.presentation.main.service.feign;

import com.logging.presentation.api.OrchestratorApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "orchestratorClient", url = "${feign.orchestrator}")
public interface OrchestratorClient extends OrchestratorApi {

}
