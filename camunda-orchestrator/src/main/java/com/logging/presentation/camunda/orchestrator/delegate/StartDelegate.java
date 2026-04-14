package com.logging.presentation.camunda.orchestrator.delegate;

import com.logging.presentation.api.request.MainServiceStartClaimRequest;
import com.logging.presentation.camunda.orchestrator.feign.MainServiceClient;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class StartDelegate implements JavaDelegate {

  private final MainServiceClient mainServiceClient;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    mainServiceClient.start(new MainServiceStartClaimRequest(
        (UUID) execution.getVariable("claimId"),
        (Long) execution.getVariable("clientId")
    ));
    log.info("Выполнено \"{}\"", execution.getCurrentActivityName());
  }
}
