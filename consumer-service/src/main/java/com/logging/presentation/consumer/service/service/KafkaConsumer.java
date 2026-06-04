package com.logging.presentation.consumer.service.service;

import com.logging.presentation.api.kafka.ExecutionEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topic:claim}")
    public void listenMessage(ConsumerRecord<String, ExecutionEvent> consumerRecord) {
        ExecutionEvent executionEvent = consumerRecord.value();
        log.info("Обработка сообщения: {}", executionEvent.getContent());
    }
}
