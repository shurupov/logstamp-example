package com.logging.presentation.delivery.adapter.controller;

import io.github.shurupov.logstamp.CachedBodyHttpServletRequest;
import io.github.shurupov.logstamp.extractor.HttpRequestStampExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class FromDeliveryIdentifierExtractor implements HttpRequestStampExtractor {

    private static final Pattern URL_CALLBACK_PATTERN = Pattern.compile("/v1/executions/([^/]+)/delivered");

    @Override
    public Map<String, String> typedExtract(CachedBodyHttpServletRequest request) {

        try {

            Matcher matcher = URL_CALLBACK_PATTERN.matcher(request.getServletPath());

            if (!matcher.matches()) {
                return Map.of();
            }

            String textExecutionId = matcher.group(1);

            return Map.of("executionId", textExecutionId);
        } catch (Exception e) {
            log.warn("Не удалось вычитать параметр executionId из запроса", e);
            return Map.of();
        }
    }
}
