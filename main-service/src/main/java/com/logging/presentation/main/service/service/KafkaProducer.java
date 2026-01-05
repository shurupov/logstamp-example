package com.logging.presentation.main.service.service;

import com.logging.presentation.api.kafka.ClaimEvent;
import com.logging.presentation.logging.starter.cross.identifier.ContextHolder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
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
    private final ContextHolder contextHolder;

    public void sendMessage(ClaimEvent claimEvent) {
        String claimId = claimEvent.getClaimId().toString();
        ProducerRecord<String, ClaimEvent> producerRecord = new ProducerRecord<>(kafkaTopic, claimId, claimEvent);
        contextHolder.addKafkaHeaders(producerRecord);
        kafkaTemplate.send(producerRecord);
    }
}
