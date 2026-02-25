package com.logging.presentation.logging.starter.cross.identifier;

public interface HttpRequestIdentifierExtractor extends IdentifierExtractor<CachedBodyHttpServletRequest> {

  @Override
  default boolean canExtract(Object container) {
    return container instanceof CachedBodyHttpServletRequest;
  }
}
