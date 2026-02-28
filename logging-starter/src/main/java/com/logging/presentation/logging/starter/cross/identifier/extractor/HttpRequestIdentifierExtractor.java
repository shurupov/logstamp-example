package com.logging.presentation.logging.starter.cross.identifier.extractor;

import com.logging.presentation.logging.starter.cross.identifier.CachedBodyHttpServletRequest;

public interface HttpRequestIdentifierExtractor extends IdentifierExtractor<CachedBodyHttpServletRequest> {

  @Override
  default boolean canExtract(Object container) {
    return container instanceof CachedBodyHttpServletRequest;
  }
}
