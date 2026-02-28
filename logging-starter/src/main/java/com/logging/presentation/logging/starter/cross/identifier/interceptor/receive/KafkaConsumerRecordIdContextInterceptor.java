package com.logging.presentation.logging.starter.cross.identifier.interceptor.receive;

import com.logging.presentation.logging.starter.cross.identifier.ContextHolder;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.RecordInterceptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaConsumerRecordIdContextInterceptor<K, V> implements RecordInterceptor<K, V> {

  private final ContextHolder contextHolder;

  @Override
  public ConsumerRecord<K, V> intercept(ConsumerRecord<K, V> record, Consumer<K, V> consumer) {
    contextHolder.addInitiator("kafka");
    contextHolder.addIdentifiers(record);
    return record;
  }

  @Override
  public void afterRecord(ConsumerRecord<K, V> record, Consumer<K, V> consumer) {
    contextHolder.clear();
  }
}
