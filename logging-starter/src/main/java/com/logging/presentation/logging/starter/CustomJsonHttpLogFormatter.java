package com.logging.presentation.logging.starter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.zalando.logbook.HttpMessage;
import org.zalando.logbook.StructuredHttpLogFormatter;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

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
