package com.logging.presentation.logging.starter.cross.identifier;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import static com.logging.presentation.logging.starter.cross.identifier.IdentifierConverter.toCamelCase;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "extractLoggingContextFilter", urlPatterns = "/*")
@RequiredArgsConstructor
public class ExtractLoggingContextFilter extends OncePerRequestFilter {

    @Value("${logging-context.header-name-prefix:x-}")
    private final String headerNamePrefix;
    private final ContextHolder contextHolder;
    private final List<CustomIdentifierExtractor> customIdentifierExtractors;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        CachedBodyHttpServletRequest requestWrapper = new CachedBodyHttpServletRequest(request);

        addEntriesToContext(requestWrapper);
        try {
            filterChain.doFilter(requestWrapper, response);
        } finally {
            contextHolder.clear();
        }
    }

    private void addEntriesToContext(CachedBodyHttpServletRequest request) {

        for (String headerName: Collections.list(request.getHeaderNames())) {
            if (!headerName.startsWith(headerNamePrefix)) {
                continue;
            }
            extractFromHeader(headerName, request);
        }
        for(CustomIdentifierExtractor extractor : customIdentifierExtractors) {
            contextHolder.add(extractor.extract(request));
        }
    }

    private void extractFromHeader(String headerName, HttpServletRequest request) {
        String headerValue = request.getHeader(headerName);
        String contextEntryName = toCamelCase(headerName.substring(headerNamePrefix.length()));
        contextHolder.add(contextEntryName, headerValue);
    }
}
