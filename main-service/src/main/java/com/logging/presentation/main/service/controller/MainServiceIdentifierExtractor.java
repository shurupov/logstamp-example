package com.logging.presentation.main.service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logging.presentation.logging.starter.cross.identifier.CachedBodyHttpServletRequest;
import com.logging.presentation.logging.starter.cross.identifier.CustomIdentifierExtractor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class MainServiceIdentifierExtractor implements CustomIdentifierExtractor {

    private final ObjectMapper objectMapper;

    @Override
    public Map<String, String> extract(CachedBodyHttpServletRequest request) {

        try {
            if (!request.getServletPath().equals("/v1/claims")) {
                return Map.of();
            }
            String body = request.getBodyAsString();
            RequestIdContainer startClaimRequest = objectMapper.readValue(body, RequestIdContainer.class);
            return Map.of("requestId", startClaimRequest.getRequestId());
        } catch (Exception e) {
            log.warn("Не удалось вычитать параметр requestId из запроса", e);
            return Map.of();
        }
    }

    @Data
    public static class RequestIdContainer {
        private String requestId;
    }
}
