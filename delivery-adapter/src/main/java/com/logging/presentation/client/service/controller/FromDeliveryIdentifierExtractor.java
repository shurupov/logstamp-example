package com.logging.presentation.client.service.controller;

import com.logging.presentation.logging.starter.cross.identifier.CachedBodyHttpServletRequest;
import com.logging.presentation.logging.starter.cross.identifier.HttpRequestIdentifierExtractor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Slf4j
@RequiredArgsConstructor
public class FromDeliveryIdentifierExtractor implements HttpRequestIdentifierExtractor {

    private static final Pattern URL_CALLBACK_PATTERN = Pattern.compile("/v1/deliveries/([^/]+)/delivered");

    @Override
    public Map<String, String> typedExtract(CachedBodyHttpServletRequest request) {

        try {

            Matcher matcher = URL_CALLBACK_PATTERN.matcher(request.getServletPath());

            if (!matcher.matches()) {
                return Map.of();
            }

            String textClaimId = matcher.group(1);

            return Map.of("claimId", textClaimId);
        } catch (Exception e) {
            log.warn("Не удалось вычитать параметр claimId из запроса", e);
            return Map.of();
        }
    }
}
