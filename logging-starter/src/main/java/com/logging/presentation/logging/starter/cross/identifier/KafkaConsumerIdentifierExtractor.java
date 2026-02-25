package com.logging.presentation.logging.starter.cross.identifier;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.header.Header;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumerIdentifierExtractor implements IdentifierExtractor<ConsumerRecord<String, ?>> {

  @Override
  public boolean canExtract(Object container) {
    return container instanceof ConsumerRecord;
  }

  @Override
  public Map<String, String> typedExtract(ConsumerRecord<String, ?> consumerRecord) {
    Map<String, String> extracted = new HashMap<>();

    for (Header header : consumerRecord.headers()) {
      if (header.key().startsWith("_")) {
        continue;
      }
      extracted.put(header.key(), new String(header.value()));
    }

    return extracted;
  }
}
