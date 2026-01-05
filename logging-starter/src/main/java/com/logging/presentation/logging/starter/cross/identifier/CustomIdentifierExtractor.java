package com.logging.presentation.logging.starter.cross.identifier;

import java.util.Map;

public interface CustomIdentifierExtractor {
    Map<String, String> extract(CachedBodyHttpServletRequest request);
}
