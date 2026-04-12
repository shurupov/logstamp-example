package com.logging.presentation.camunda.orchestrator.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

  @Bean
  public FilterRegistrationBean<CorsFilter> customCorsFilter() {
    // 1. Настраиваем политики CORS
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();

    // Важные настройки для Camunda REST API
    config.setAllowCredentials(true);               // Разрешить куки/авторизацию
    config.addAllowedOriginPattern("*");            // ЛЮБОЙ домен (или укажите конкретный)
    config.addAllowedHeader("*");                   // Все заголовки
    config.addAllowedMethod("*");                   // Все методы (GET, POST, PUT, DELETE, OPTIONS)
    config.addExposedHeader("Authorization");       // Нужно для авторизации

    // Применяем эти правила ко всем эндпоинтам
    source.registerCorsConfiguration("/**", config);

    // 2. Создаём фильтр CORS на основе нашей конфигурации
    CorsFilter corsFilter = new CorsFilter(source);

    // 3. РЕГИСТРИРУЕМ фильтр и задаём ему МАКСИМАЛЬНЫЙ приоритет (выполнится первым)
    FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>(corsFilter);
    registration.setOrder(Ordered.HIGHEST_PRECEDENCE); // <-- КЛЮЧЕВОЙ МОМЕНТ
    registration.setUrlPatterns(java.util.Collections.singletonList("/*"));

    return registration;
  }
}
