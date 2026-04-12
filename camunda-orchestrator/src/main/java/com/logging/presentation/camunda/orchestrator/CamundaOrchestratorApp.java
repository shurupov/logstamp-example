package com.logging.presentation.camunda.orchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CamundaOrchestratorApp {

  public static void main(String[] args) {
    SpringApplication.run(CamundaOrchestratorApp.class, args);
  }
}