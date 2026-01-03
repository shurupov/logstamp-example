package com.logging.presentation.logging.starter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Logbook;
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
}
