package com.logging.presentation.logging.starter.cross.identifier.extractor;

import java.util.Map;

public interface IdentifierExtractor<T> {
  boolean canExtract(Object container);
  default Map<String, String> extract(Object container) {
    return typedExtract((T) container);
  }

  Map<String, String> typedExtract(T container);
}
