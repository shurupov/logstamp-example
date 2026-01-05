package com.logging.presentation.logging.starter.cross.identifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddCrossIdentifier {
    String objectFieldName();
    String identifierFieldName();
    String initiator();
}
