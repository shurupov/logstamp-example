package com.logging.presentation.logging.starter.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {


//  private final KafkaProperties properties;

  /*@Bean
  @ConditionalOnMissingBean(name = "kafkaListenerContainerFactory")
  ConcurrentKafkaListenerContainerFactory<?, ?> kafkaListenerContainerFactory(
      ConcurrentKafkaListenerContainerFactoryConfigurer configurer,
      ObjectProvider<ConsumerFactory<Object, Object>> kafkaConsumerFactory,
      ObjectProvider<ContainerCustomizer<Object, Object, ConcurrentMessageListenerContainer<Object, Object>>> kafkaContainerCustomizer,
      KafkaConsumerRecordIdContextInterceptor<?, ?> kafkaConsumerRecordIdContextInterceptor) {
    ConcurrentKafkaListenerContainerFactory<Object, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
    configurer.configure(factory, kafkaConsumerFactory
        .getIfAvailable(() -> new DefaultKafkaConsumerFactory<>(this.properties.buildConsumerProperties())));
    kafkaContainerCustomizer.ifAvailable(factory::setContainerCustomizer);
    factory.setRecordInterceptor(kafkaConsumerRecordIdContextInterceptor);
    return factory;
  }*/
}
