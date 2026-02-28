package com.logging.presentation.logging.starter.cross.identifier.interceptor.pass;

import com.logging.presentation.logging.starter.cross.identifier.ContextHolder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducerIdContextInterceptor<K, V> implements ProducerInterceptor<K, V> {

  private final ContextHolder contextHolder;

  @Override
  public ProducerRecord<K, V> onSend(ProducerRecord<K, V> record) {
    for (Map.Entry<String, String> contextEntry : contextHolder.get().entrySet()) {
      record.headers().add(contextEntry.getKey(), contextEntry.getValue().getBytes(StandardCharsets.UTF_8));
    }
    return record;
  }

  @Override
  public void onAcknowledgement(RecordMetadata metadata, Exception exception) {

  }

  @Override
  public void close() {

  }

  @Override
  public void configure(Map<String, ?> configs) {

  }
}
