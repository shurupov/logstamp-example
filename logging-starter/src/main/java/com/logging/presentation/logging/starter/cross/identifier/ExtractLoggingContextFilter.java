package com.logging.presentation.logging.starter.cross.identifier;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@WebFilter(filterName = "extractLoggingContextFilter", urlPatterns = "/*")
@RequiredArgsConstructor
public class ExtractLoggingContextFilter extends OncePerRequestFilter {

  private final ContextHolder contextHolder;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    CachedBodyHttpServletRequest requestWrapper = new CachedBodyHttpServletRequest(request);

    contextHolder.addInitiator("rest");
    contextHolder.addIdentifiers(requestWrapper);
    try {
      filterChain.doFilter(requestWrapper, response);
    } finally {
      contextHolder.clear();
    }
  }
}
