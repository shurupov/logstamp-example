package com.logging.presentation.logging.starter.cross.identifier;

import java.lang.reflect.Method;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class AddIdentifierAspect {

  private final ContextHolder contextHolder;

  @Around("@annotation(com.logging.presentation.logging.starter.cross.identifier.AddIdentifiers)")
  public Object addCrossIdentifier(ProceedingJoinPoint joinPoint) throws Throwable {
    try {
      if (joinPoint.getArgs().length > 0) {
        Object argument = joinPoint.getArgs()[0];
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        AddIdentifiers annotation = method.getAnnotation(AddIdentifiers.class);
        contextHolder.addInitiator(annotation.initiator());
        contextHolder.addIdentifiers(argument);
      }
      return joinPoint.proceed();
    } finally {
      contextHolder.clear();
    }
  }
}
