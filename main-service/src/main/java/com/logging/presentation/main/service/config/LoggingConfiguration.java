package com.logging.presentation.main.service.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.HttpMessage;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.StructuredHttpLogFormatter;
import org.zalando.logbook.json.PrettyPrintingJsonBodyFilter;
import org.zalando.logbook.logstash.LogstashLogbackSink;

@Configuration
public class LoggingConfiguration {

  @Bean
  public Logbook logbook() {
    HttpLogFormatter formatter = new CustomJsonHttpLogFormatter();
    LogstashLogbackSink logstashsink = new LogstashLogbackSink(formatter);
    return Logbook.builder()
        .bodyFilter(new PrettyPrintingJsonBodyFilter())
        .sink(logstashsink)
        .build();
  }

  public class CustomJsonHttpLogFormatter implements StructuredHttpLogFormatter {

    private final ObjectMapper mapper;

    public CustomJsonHttpLogFormatter() {
      this(new ObjectMapper());
    }

    public CustomJsonHttpLogFormatter(final ObjectMapper mapper) {
      this.mapper = mapper;
    }

    public Optional<Object> prepareBody(final HttpMessage message) throws IOException {
      String body = message.getBodyAsString();
      if (body.isEmpty()) {
        return Optional.empty();
      } else {
        return Optional.of(body);
      }
    }

    public String format(final Map<String, Object> content) throws IOException {
      return this.mapper.writeValueAsString(content);
    }
  }
}
