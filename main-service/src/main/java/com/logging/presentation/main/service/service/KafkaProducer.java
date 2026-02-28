package com.logging.presentation.main.service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.logging.presentation.api.kafka.ClaimEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${kafka.topic:claim}")
    private final String kafkaTopic;

    private final KafkaTemplate<String, ClaimEvent> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(ClaimEvent claimEvent) {
        String claimId = claimEvent.getClaimId().toString();
        ProducerRecord<String, ClaimEvent> producerRecord = new ProducerRecord<>(kafkaTopic, claimId, claimEvent);

      try {
        MDC.put("kafka.body", objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(claimEvent));
        MDC.put("kafka.action", "produce");
        MDC.put("kafka.topic", "claim");
        log.trace("{} sent:", claimEvent.getClass().getSimpleName());
      } catch (JsonProcessingException e) {
        log.warn(e.getMessage(), e);
      }
      kafkaTemplate.send(producerRecord);

      MDC.remove("kafka.body");
      MDC.remove("kafka.action");
      MDC.remove("kafka.topic");
    }
}
