package com.logging.presentation.logging.starter.config;

import com.logging.presentation.logging.starter.logbook.CustomJsonHttpLogFormatter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.zalando.logbook.HttpLogFormatter;
import org.zalando.logbook.Logbook;
import org.zalando.logbook.json.PrettyPrintingJsonBodyFilter;
import org.zalando.logbook.logstash.LogstashLogbackSink;

@Configuration
@ComponentScan("com.logging.presentation.logging.starter")
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
