package com.logging.presentation.logging.starter.cross.identifier;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class ContextHolder {

  private static final String INITIATOR_FIELD_NAME = "initiator";

  private final ThreadLocal<Map<String, String>> context = new ThreadLocal<>();
  private final List<IdentifierExtractor<?>> identifierExtractors;

  public void addKafkaHeaders(ProducerRecord<String, ?> record) {
    for (Map.Entry<String, String> contextEntries : get().entrySet()) {
      record.headers().add(contextEntries.getKey(), contextEntries.getValue().getBytes(StandardCharsets.UTF_8));
    }
  }

  public void addInitiator(String initiator) {
    MDC.put(INITIATOR_FIELD_NAME, initiator);
  }

  public Map<String, String> get() {
    if (context.get() == null) {
      context.set(new HashMap<>());
    }
    return context.get();
  }

  public void add(Map<String, String> context) {
    for (Map.Entry<String, String> contextEntry : context.entrySet()) {
      add(contextEntry.getKey(), contextEntry.getValue());
    }
  }

  public void add(String name, String value) {
    get().put(name, value);
    MDC.put(name, value);
  }

  public void clear() {
    for (String contextEntryKey : get().keySet()) {
      MDC.remove(contextEntryKey);
    }
    MDC.remove(INITIATOR_FIELD_NAME);
    context.remove();
  }

  public void addIdentifiers(Object event) {
    for (IdentifierExtractor<?> extractor : identifierExtractors) {
      if (extractor.canExtract(event)) {
        try {
          Map<String, String> extracted = extractor.extract(event);
          add(extracted);
        } catch (Exception e) {
          log.warn("Не удалось извлечь идентификаторы", e);
        }
      }
    }
  }
}
