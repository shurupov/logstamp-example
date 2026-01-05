package com.logging.presentation.logging.starter.cross.identifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
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

    private static final String BODY_FIELD_NAME_TEMPLATE = "%s.body";

    private final ObjectMapper objectMapper;
    private final ContextHolder contextHolder;
    
    @Around("@annotation(org.springframework.kafka.annotation.KafkaListener)")
    public Object addCrossIdFromKafkaEvent(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            if (joinPoint.getArgs().length > 0) {
                contextHolder.addInitiator("kafka");
                Object event = joinPoint.getArgs()[0];
                if (event instanceof ConsumerRecord<?,?> consumerRecord) {
                    addCrossIdentifiers(consumerRecord);
                    logObject(
                            consumerRecord.value(),
                            String.format(BODY_FIELD_NAME_TEMPLATE, "kafka.event")
                    );
                }
            }
            return joinPoint.proceed();
        } finally {
            contextHolder.clear();
        }
    }

    protected void addCrossIdentifiers(ConsumerRecord<?,?> consumerRecord) {

        for (Header header : consumerRecord.headers()) {
            if (header.key().startsWith("_")) {
                continue;
            }
            contextHolder.add(header.key(), new String(header.value()));
        }
    }

    //TODO Перетащить в отдельный класс/бин/абстрактный класс
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
