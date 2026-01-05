package com.logging.presentation.logging.starter.cross.identifier;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
//TODO Перетащить этот аспект в отдельный стартер
public class AddCrossIdentifierAspect {

    private final ObjectMapper objectMapper;
    private final ContextHolder contextHolder;

    @Around("@annotation(AddCrossIdentifier)")
    public Object addCrossIdentifier(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            if (joinPoint.getArgs().length > 0) {
                Object event = joinPoint.getArgs()[0];
                AddCrossIdentifier annotation = ((MethodSignature) joinPoint.getSignature()).getMethod().getAnnotation(AddCrossIdentifier.class);
                contextHolder.addInitiator(annotation.initiator());
                addIdentifier(event, annotation.identifierFieldName());
                logObject(event, annotation.objectFieldName());
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

    private void addIdentifier(Object event, String logIdentifierFieldName) {
        try {
            Field field = event.getClass().getDeclaredField(logIdentifierFieldName);
            field.setAccessible(true);
            String value = field.get(event).toString();
            MDC.put(logIdentifierFieldName, value);
        } catch (Exception e) {
            log.warn("Не удалось получить поле {}", logIdentifierFieldName, e);
        }
    }
}
