package com.logging.presentation.logging.starter.cross.identifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaConsumerAddIdentifiersAspect {

  private final ObjectMapper objectMapper;
  private final ContextHolder contextHolder;

  @Around("@annotation(org.springframework.kafka.annotation.KafkaListener)")
  public Object addCrossIdFromKafkaEvent(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      if (joinPoint.getArgs().length > 0) {
        Object event = joinPoint.getArgs()[0];
        contextHolder.addInitiator("kafka");
        contextHolder.addIdentifiers(event);
        if (event instanceof ConsumerRecord<?,?> consumerRecord) {
          logObject(consumerRecord.value(), "kafka.event.body");
        }
      }
      return joinPoint.proceed();
    } finally {
      contextHolder.clear();
    }
  }

  private void logObject(Object event, String logFieldName) {
    String prettyEvent;
    try {
      prettyEvent = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(event);
    } catch (JsonProcessingException e) {
      prettyEvent = event != null ? event.toString() : null;
    }
    MDC.put(logFieldName, prettyEvent);
    log.trace("Получено {}", event != null ? event.getClass().getSimpleName() : null);
    MDC.remove(logFieldName);
  }
}
